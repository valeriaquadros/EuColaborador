using AutoMapper;
using CrossCutting.Configurations;
using CrossCutting.Constants;
using CrossCutting.Services;
using Data.Contexts;
using Domain.DTO;
using Domain.Entities;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.WebUtilities;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;
using System;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Text.Encodings.Web;
using System.Threading.Tasks;

namespace Api.Application
{
    public class UsuarioManager
    {
        private readonly SignInManager<Usuario> _signInManager;
        private readonly UserManager<Usuario> _userManager;
        private readonly AppSettings _appSettings;
        private readonly IMapper _mapper;
        private readonly CustomContext _context;
        private readonly IEmailSender _emailSender;

        public UsuarioManager(SignInManager<Usuario> signInManager,
                                UserManager<Usuario> userManager,
                                IOptions<AppSettings> appSettings,
                                IMapper mapper,
                                CustomContext context,
                                IEmailSender emailSender)
        {
            _signInManager = signInManager;
            _userManager = userManager;
            _appSettings = appSettings.Value;
            _mapper = mapper;
            _context = context;
            _emailSender = emailSender;
        }

        public async Task<IdentityResult> CriarUserAsync(UsuarioInputModel registerUser)
        {
            var user = await _userManager.FindByNameAsync(registerUser.CPF);
            if (user != null)
                return GetIdentityResultFailed(StatusCodes.Status422UnprocessableEntity, "Usuário já cadastrado");

            Pessoa pessoa = _context.Pessoa.FirstOrDefault(x => x.Cpf == registerUser.CPF);
            if (pessoa != null)
                user = new Usuario(registerUser, pessoa);
            else
                user = new Usuario(registerUser);

            var result = await _userManager.CreateAsync(user, registerUser.Password);
            if (result.Succeeded)
            {
                await EnviarEmailConfirmacao(user.Email);
            }
            return result;
        }

        public async Task<UsuarioOutputModel> AdicionarPerfilAdmin(UsuarioInputModel registerUser)
        {
            var user = await _userManager.FindByNameAsync(registerUser.CPF);
            await _userManager.AddToRoleAsync(user, Perfis.Administrador);
            return new UsuarioOutputModel(user);
        }

        public async Task EnviarEmailConfirmacao(string email)
        {
            var user = await _userManager.FindByEmailAsync(email);
            var code = await _userManager.GenerateEmailConfirmationTokenAsync(user);
            code = WebEncoders.Base64UrlEncode(Encoding.UTF8.GetBytes(code));
            var recoverUrl = Environment.GetEnvironmentVariable("WEBURL") + "/usuario/confirmaremail?userId=" + user.Id + "&code=" + code;
            var encodedRecoverUrl = HtmlEncoder.Default.Encode(recoverUrl);

            try
            {
                await _emailSender.SendEmailAsync(user.Email,
                            "Eu, Colaborador-Confirmação de email",
                            $"<p>Olá {user.Pessoa.Nome}</p>"+
                            "<p>Para confirmar o e-mail cadastrado em sua conta, clique no link abaixo:</p>" +
                            $"<a href='{encodedRecoverUrl}'>{encodedRecoverUrl}</a>." +
                            $"<p>Eu, Colaborador</p>");
            }
            catch (Exception)
            {
                Console.WriteLine("Email não enviado");
            }
        }

        public async Task<bool> ConfimarEmail(string userId, string code)
        {
            if (userId == null || code == null)
            {
                return false;
            }

            var user = await _userManager.FindByIdAsync(userId);
            if (user == null)
            {
                return false;
            }

            code = Encoding.UTF8.GetString(WebEncoders.Base64UrlDecode(code));
            var result = await _userManager.ConfirmEmailAsync(user, code);
            return result.Succeeded;
        }

        public async Task<LoginUsarioOutputModel> AtualizarUserAsync(HttpContext context, AtualizarUsuarioInputModel input)
        {
            var user = await _userManager.FindByNameAsync(context.User.FindFirst(ClaimTypes.Name).Value);

            user.UpdateUser(input);

            var result = await _userManager.UpdateAsync(user);
            if (result.Succeeded)
            {
                await _signInManager.RefreshSignInAsync(user);
                return new LoginUsarioOutputModel(new UsuarioOutputModel(user), await GerarJwt(user.UserName));
            }
            else
                return null;
        }

        public async Task<LoginUsarioOutputModel> LoginAsync(LoginUsuarioInputModel loginUser)
        {
            var user = await _userManager.FindByNameAsync(loginUser.Login);
            if (user == null)
                return null;

            user.Pessoa = await _context.Pessoa.FindAsync(user.PessoaId);
            var result = await _signInManager.PasswordSignInAsync(loginUser.Login, loginUser.Password, false, true);

            if (result.Succeeded)
                return new LoginUsarioOutputModel(new UsuarioOutputModel(user), await GerarJwt(loginUser.Login));

            return null;
        }

        public async Task<UsuarioOutputModel> GetUserAsync(string id)
        {
            var user = await _userManager.FindByIdAsync(id);
            user.Pessoa = await _context.Pessoa.FindAsync(user.PessoaId);
            var userMapped = _mapper.Map<UsuarioOutputModel>(user);

            return userMapped;
        }

        public async Task<bool> DeleteUserAsync(HttpContext context)
        {
            var user = await _userManager.FindByNameAsync(context.User.FindFirst(ClaimTypes.Name).Value);
            IdentityResult identityResult = await _userManager.DeleteAsync(user);
            return identityResult.Succeeded;
        }

        public async Task<bool> RecuperarSenhaAsync(RecuperarSenhaInputModel recoverPasswordInput)
        {
            var user = await _userManager.FindByEmailAsync(recoverPasswordInput.Email);

            var code = await _userManager.GeneratePasswordResetTokenAsync(user);
            code = WebEncoders.Base64UrlEncode(Encoding.UTF8.GetBytes(code));
            var recoverUrl = Environment.GetEnvironmentVariable("WEBURL") + "/usuario/resetarsenha?code=" + code;
            var encodedRecoverUrl = HtmlEncoder.Default.Encode(recoverUrl);

            try
            {
                await _emailSender.SendEmailAsync(recoverPasswordInput.Email,
                                                "Eu, Colaborador-Recuperação de senha",
                                                $"<p>Olá {user.Pessoa.Nome}</p>" +
                                                "<p>Para realizar a recuperação de sua senha, clique no link abaixo:</p>" +
                                                $"<a href='{encodedRecoverUrl}'>{encodedRecoverUrl}</a>." +
                                                $"<p>Eu, Colaborador</p>");
                return true;
            }
            catch (Exception)
            {
                return false;
            }

        }

        public async Task<bool> ResetarSenhaAsync(ResetSenhaInputModel Input)
        {
            var user = await _userManager.FindByEmailAsync(Input.Email);
            var code = Encoding.UTF8.GetString(WebEncoders.Base64UrlDecode(Input.Code));
            var result = await _userManager.ResetPasswordAsync(user, code, Input.Senha);
            return result.Succeeded;
        }

        public async Task<bool> AlterarSenhaAsync(HttpContext context, AlterarSenhaInputModel input)
        {
            var user = await _userManager.FindByNameAsync(context.User.FindFirst(ClaimTypes.Name).Value);

            if (user == null)
            {
                return false;
            }

            var result = await _userManager.ChangePasswordAsync(user, input.SenhaAntiga, input.NovaSenha);

            if (result.Succeeded)
            {
                await _signInManager.RefreshSignInAsync(user);
            }

            return result.Succeeded;
        }

        public async Task<bool> AlterarEmailAsync(HttpContext context, AlterarEmailInputModel input)
        {
            var user = await _userManager.FindByNameAsync(context.User.FindFirst(ClaimTypes.Name).Value);
            if (user == null)
            {
                return false;
            }

            var email = await _userManager.GetEmailAsync(user);
            if (input.NovoEmail != email)
            {
                var code = await _userManager.GenerateChangeEmailTokenAsync(user, input.NovoEmail);
                code = WebEncoders.Base64UrlEncode(Encoding.UTF8.GetBytes(code));
                var recoverUrl = Environment.GetEnvironmentVariable("WEBURL") + "/usuario/confirmaralteraremail?userId="+user.Id+"&email="+input.NovoEmail+"&code=" + code;
                var encodedRecoverUrl = HtmlEncoder.Default.Encode(recoverUrl);

                try
                {
                    await _emailSender.SendEmailAsync(input.NovoEmail,
                                                    "Eu, Colaborador-Confirmação de alteração de email",
                                                    $"<p>Olá {user.Pessoa.Nome}</p>" +
                                                    "<p>Para confirmar a alteração de seu e-mail, clique no link abaixo:</p>" +
                                                    $"<a href='{encodedRecoverUrl}'>{encodedRecoverUrl}</a>." +
                                                    $"<p>Eu, Colaborador</p>");
                    return true;
                }
                catch (Exception e)
                {
                    return false;
                }
            }
            return true;
        }

        public async Task<bool> ConfirmarAlterarEmailAsync(string userId, string email, string code)
        {
            if (userId == null || email == null || code == null)
            {
                return false;
            }

            var user = await _userManager.FindByIdAsync(userId);
            if (user == null)
            {
                return false;
            }

            code = Encoding.UTF8.GetString(WebEncoders.Base64UrlDecode(code));
            var result = await _userManager.ChangeEmailAsync(user, email, code);
            return result.Succeeded;
        }

        private async Task<string> GerarJwt(string login)
        {
            var user = await _userManager.FindByNameAsync(login);

            var identityClaims = new ClaimsIdentity();
            identityClaims.AddClaim(new Claim(ClaimTypes.Name, user.UserName));
            identityClaims.AddClaim(new Claim(ClaimTypes.GivenName, user.Pessoa.Nome));
            var perfis = await _userManager.GetRolesAsync(user);
            if(perfis.Count>0)
                identityClaims.AddClaim(new Claim(ClaimTypes.Role, perfis.FirstOrDefault()));

            var tokenHandler = new JwtSecurityTokenHandler();
            var key = Encoding.ASCII.GetBytes(_appSettings.Secret);
            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Subject = identityClaims,
                Expires = DateTime.UtcNow.AddHours(_appSettings.ExpiracaoHoras),
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature)
            };

            return tokenHandler.WriteToken(tokenHandler.CreateToken(tokenDescriptor));
        }

        private IdentityResult GetIdentityResultFailed(int code, string description)
        {
            return IdentityResult.Failed(new IdentityError()
            {
                Code = code.ToString(),
                Description = description
            });
        }
    }
}
