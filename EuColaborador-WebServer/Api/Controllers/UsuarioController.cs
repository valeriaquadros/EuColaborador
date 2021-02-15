using Api.Application;
using CrossCutting.Constants;
using Domain.DTO;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using System.Linq;
using System.Threading.Tasks;

namespace Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsuarioController : ControllerBase
    {
        private readonly UsuarioManager _usuarioManager;

        public UsuarioController(UsuarioManager usuarioManager)
        {
            _usuarioManager = usuarioManager;
        }

        [AllowAnonymous]
        [HttpPost]
        public async Task<ActionResult<LoginUsarioOutputModel>> CadastrarMorador([FromBody]UsuarioInputModel input)
        {

            if (!ModelState.IsValid) return BadRequest("Dados inválidos");

            IdentityResult result = await _usuarioManager.CriarUserAsync(input);
            
            LoginUsarioOutputModel user=null;
            if (result.Succeeded)
                user = await _usuarioManager.LoginAsync(new LoginUsuarioInputModel(input.CPF, input.Password));

            if (user != null)
                return Ok(user);
            
            return BadRequest(result.Errors.FirstOrDefault().Description);
        }

        [Authorize(Roles = Perfis.Administrador)]
        [HttpPost("CadastrarAdmin")]
        public async Task<ActionResult<LoginUsarioOutputModel>> CadastrarAdmin([FromBody] UsuarioInputModel input)
        {
            if (!ModelState.IsValid) return BadRequest("Dados inválidos");

            IdentityResult result = await _usuarioManager.CriarUserAsync(input);
            UsuarioOutputModel user = null;
            if (result.Succeeded)
                user = await _usuarioManager.AdicionarPerfilAdmin(input);

            if (user != null)
                return CreatedAtAction("Get", new { id = user.Id }, user);

            return BadRequest(result.Errors.FirstOrDefault().Description);
        }

        [Authorize]
        [HttpPut]
        public async Task<ActionResult<LoginUsarioOutputModel>> Put([FromBody]AtualizarUsuarioInputModel input)
        {
            if (!ModelState.IsValid) return BadRequest("Dados inválidos");

            var user = await _usuarioManager.AtualizarUserAsync(HttpContext, input);
            if (user != null)
            {
                return Ok(user);
            }
            return BadRequest();
        }

        [Authorize]
        [HttpGet("{id}")]
        public async Task<ActionResult<UsuarioOutputModel>> GetAsync(string id)
        {
            var result = await _usuarioManager.GetUserAsync(id);
            if (result != null)
            {
                return Ok(result);
            }

            return BadRequest();
        }

        [Authorize]
        [HttpDelete]
        public async Task<ActionResult<UsuarioOutputModel>> DeleteAsync()
        {
            bool result = await _usuarioManager.DeleteUserAsync(HttpContext);
            if (result)
                return Ok();
            else
                return BadRequest();
        }

        [AllowAnonymous]
        [HttpGet("EnviarEmailConfirmacao")]
        public async Task<ActionResult> EnviarEmailConfirmacao(string email)
        {
            await _usuarioManager.EnviarEmailConfirmacao(email);
            return Ok();
        }

        [AllowAnonymous]
        [HttpGet("ConfimarEmail")]
        public async Task<ActionResult> ConfimarEmail(string userId, string code)
        {
            var result = await _usuarioManager.ConfimarEmail(userId, code);
            if (result)
            {
                return Ok();
            }
            return BadRequest();
        }

        [AllowAnonymous]
        [HttpPost("Login")]
        public async Task<ActionResult<LoginUsarioOutputModel>> Login([FromBody]LoginUsuarioInputModel input)
        {
            if (!ModelState.IsValid) return BadRequest("Dados inválidos");

            var result = await _usuarioManager.LoginAsync(input);
            if (result != null)
            {
                return Ok(result);
            }

            return BadRequest("Usuário ou senha inválido");
        }

        [AllowAnonymous]
        [HttpPost("RecuperarSenha")]
        public async Task<ActionResult> RecuperarSenhaAsync([FromBody]RecuperarSenhaInputModel input)
        {
            if (!ModelState.IsValid) return BadRequest("Dados inválidos");

            var result = await _usuarioManager.RecuperarSenhaAsync(input);
            if (result)
            {
                return Ok();
            }

            return BadRequest();
        }

        [AllowAnonymous]
        [HttpPost("ResetarSenha")]
        public async Task<ActionResult> ResetarSenhaAsync([FromBody]ResetSenhaInputModel input)
        {
            if (!ModelState.IsValid) return BadRequest("Dados inválidos");

            await _usuarioManager.ResetarSenhaAsync(input);
            return Ok();
        }

        [Authorize]
        [HttpPost("AlterarSenha")]
        public async Task<ActionResult> AlterarSenhaAsync([FromBody]AlterarSenhaInputModel input)
        {
            if (!ModelState.IsValid) return BadRequest("Dados inválidos");

            var result = await _usuarioManager.AlterarSenhaAsync(HttpContext, input);
            if (result)
            {
                return Ok();
            }

            return BadRequest();
        }

        [Authorize]
        [HttpPost("AlterarEmail")]
        public async Task<ActionResult> AlterarEmailAsync([FromBody]AlterarEmailInputModel input)
        {
            if (!ModelState.IsValid) return BadRequest("Dados inválidos");

            var result = await _usuarioManager.AlterarEmailAsync(HttpContext, input);
            if (result)
            {
                return Ok();
            }

            return BadRequest();
        }

        [AllowAnonymous]
        [HttpGet("ConfirmarAlterarEmail")]
        public async Task<ActionResult> ConfirmarAlterarEmailAsync(string userId, string email, string code)
        {
            if (!ModelState.IsValid) return BadRequest("Dados inválidos");

            var result = await _usuarioManager.ConfirmarAlterarEmailAsync(userId, email, code);
            if (result)
            {
                return Ok();
            }

            return BadRequest();
        }
    }
}