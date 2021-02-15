using Domain.DTO;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;
using Web.Application;
using Web.Application.Util;

namespace Web.Controllers
{
    [Route("Usuario/Gerenciar")]
    public class GerenciarPerfilUsuarioController : Controller
    {
        #region Perfil
        [Authorize]
        [HttpGet]
        [Route("")]
        [Route("Perfil")]
        public IActionResult Perfil()
        {
            return View(GetAtualizarUsuarioInputModel());
        }

        [Authorize]
        [HttpPost]
        [Route("")]
        [Route("Perfil")]
        public async Task<IActionResult> PerfilAsync([FromServices] GerenciarPerfilUsuarioApp app, AtualizarUsuarioInputModel inputModel)
        {
            if (!ModelState.IsValid) return View();

            bool result = await app.AtualizarPerfil(HttpContext, inputModel);

            if (!result)
                ViewData["StatusMessage"] = "Erro ao processar sua solicitação. Tente novamente";

            return View(GetAtualizarUsuarioInputModel());
        }

        private AtualizarUsuarioInputModel GetAtualizarUsuarioInputModel()
        {
            LoginUsarioOutputModel loginUsarioOutputModel = HttpContext.Session.Get<LoginUsarioOutputModel>("Usuario");
            AtualizarUsuarioInputModel inputModel = new AtualizarUsuarioInputModel()
            {
                Nome = loginUsarioOutputModel.Usuario.Nome,
                DataNascimento = loginUsarioOutputModel.Usuario.DataNascimento,
                Sexo = loginUsarioOutputModel.Usuario.Sexo
            };
            return inputModel;
        }
        #endregion

        #region Email
        [Authorize]
        [HttpPost]
        [Route("EnviarEmailConfirmacao")]
        public async Task<IActionResult> EnviarEmailConfirmacao([FromServices] GerenciarPerfilUsuarioApp app)
        {
            bool result = await app.EnviarEmailConfirmacao(HttpContext, HttpContext.Session.Get<LoginUsarioOutputModel>("Usuario").Usuario.Email);

            if (result)
                TempData["StatusMessage"] = "Email enviado com sucesso. Por favor, verifique seu email.";
            else
                ViewData["StatusMessage"] = "Erro ao processar sua solicitação. Tente novamente";

            return RedirectToAction("Email");
        }

        [Authorize]
        [HttpGet]
        [Route("Email")]
        public IActionResult Email()
        {
            ViewData["StatusMessage"] = TempData["StatusMessage"];
            return View(GetEmailInputModel());
        }

        [Authorize]
        [HttpPost]
        [Route("Email")]
        public async Task<IActionResult> EmailAsync([FromServices] GerenciarPerfilUsuarioApp app, EmailInputModel inputModel)
        {
            if (!ModelState.IsValid) return View();

            bool result = await app.AlterarEmail(HttpContext, new AlterarEmailInputModel(inputModel.NovoEmail));

            if (result)
                ViewData["StatusMessage"] = "Confirme a alteração de email através de link enviado para o novo email solicitado.";
            else
                ViewData["StatusMessage"] = "Erro ao processar sua solicitação. Tente novamente";

            return View(GetEmailInputModel());
        }

        private EmailInputModel GetEmailInputModel()
        {
            LoginUsarioOutputModel loginUsarioOutputModel = HttpContext.Session.Get<LoginUsarioOutputModel>("Usuario");
            EmailInputModel inputModel = new EmailInputModel()
            {
                Email = loginUsarioOutputModel.Usuario.Email,
                EmailConfirmado = loginUsarioOutputModel.Usuario.EmailConfirmado
            };
            return inputModel;
        }
        #endregion

        #region AlterarSenha
        [Authorize]
        [HttpGet]
        [Route("AlterarSenha")]
        public IActionResult AlterarSenha()
        {
            return View();
        }

        [Authorize]
        [HttpPost]
        [Route("AlterarSenha")]
        public async Task<IActionResult> AlterarSenhaAsync([FromServices] GerenciarPerfilUsuarioApp app, AlterarSenhaInputModel inputModel)
        {
            if (!ModelState.IsValid) return View();

            bool result = await app.AlterarSenha(HttpContext, inputModel);

            if (result)
                ViewData["StatusMessage"] = "Senha alterada com sucesso";
            else
                ViewData["StatusMessage"] = "Erro ao processar sua solicitação. Tente novamente";

            return View();
        }
        #endregion
    }
}
