using CrossCutting.Constants;
using Domain.DTO;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using NToastNotify;
using System.Net.Http;
using System.Threading.Tasks;
using Web.Application;
using Web.Models;

namespace Web.Controllers
{
    public class UsuarioController : Controller
    {
        #region CadastrarUsuario
        [Authorize(Roles = Perfis.Administrador)]
        [HttpGet]
        public IActionResult CadastrarUsuario()
        {
            return View();
        }

        [Authorize(Roles = Perfis.Administrador)]
        [HttpPost]
        public async Task<IActionResult> CadastrarUsuarioAsync([FromServices] UsuarioApp usuarioApp, UsuarioInputModel model)
        {
            if (!ModelState.IsValid) return View();

            HttpResponseMessage result = await usuarioApp.CadastrarUsuario(HttpContext, model);

            if (result.IsSuccessStatusCode)
                return RedirectToAction("CadastrarUsuarioConfirmacao");
            else
            {
                ViewData["StatusMessage"] = "Erro: " + await result.Content.ReadAsStringAsync();
                return View();
            }
        }

        [HttpGet]
        public IActionResult CadastrarUsuarioConfirmacao()
        {
            return View();
        }
        #endregion

        #region Login
        [HttpGet]
        public IActionResult Login()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> LoginAsync([FromServices] UsuarioApp usuarioApp, LoginUsuarioInputModel model)
        {
            if (!ModelState.IsValid) return View();

            bool result = await usuarioApp.Login(HttpContext, model);

            if (result)
                return RedirectToAction("Index", "Home");
            else
            {
                ViewData["StatusMessage"] = "Login ou senha inválidos";
                return View();
            }
        }
        #endregion

        #region Logout
        [HttpPost]
        public IActionResult Logout([FromServices] IToastNotification toastNotification)
        {
            HttpContext.Session.Clear();
            toastNotification.AddInfoToastMessage("Logout realizado com sucesso!");
            return RedirectToAction("Index", "Home");
        }
        #endregion

        #region Recuperar Senha
        [HttpGet]
        public IActionResult RecuperarSenha()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> RecuperarSenhaAsync([FromServices] UsuarioApp usuarioApp, [FromForm] RecuperarSenhaInputModel model)
        {
            if (!ModelState.IsValid) return View();

            bool result = await usuarioApp.RecuperarSenha(model);

            if (result)
                return RedirectToAction("RecuperarSenhaConfirmacao");
            else
            {
                ViewData["StatusMessage"] = "Erro ao processar sua solicitação. Tente novamente";
                return View();
            }
        }

        [HttpGet]
        public IActionResult RecuperarSenhaConfirmacao()
        {
            return View();
        }
        #endregion

        #region Resetar Senha
        [HttpGet]
        public IActionResult ResetarSenha(string code = null)
        {
            if (code == null)
            {
                return BadRequest("Código de verificação de autenticidade deve ser fornecido.");
            }
            else
            {
                return View();
            }
        }

        [HttpPost]
        public async Task<IActionResult> ResetarSenhaAsync([FromServices] UsuarioApp usuarioApp, [FromForm] ResetSenhaInputModel model)
        {
            if (!ModelState.IsValid) return View();

            bool result = await usuarioApp.ResetarSenha(model);

            if (result)
                return RedirectToAction("ResetarSenhaConfirmacao");
            else
            {
                ViewData["StatusMessage"] = "Erro ao processar sua solicitação. Tente novamente";
                return View();
            }
        }

        [HttpGet]
        public IActionResult ResetarSenhaConfirmacao()
        {
            return View();
        }
        #endregion

        #region Confirmar Email
        [HttpGet]
        public async Task<IActionResult> ConfirmarEmailAsync([FromServices] UsuarioApp usuarioApp, string userId, string code)
        {
            if (userId == null || code == null)
            {
                return View(new StatusMessageViewModel("Erro ao confirmar seu e-mail."));
            }
            bool result = await usuarioApp.ConfirmarEmail(userId, code);
            StatusMessageViewModel statusMessageViewModel;
            if (result)
                statusMessageViewModel = new StatusMessageViewModel("Obrigado por confirmar seu e-mail.");
            else
                statusMessageViewModel = new StatusMessageViewModel("Erro ao confirmar seu e-mail.");

            return View(statusMessageViewModel);
        }
        #endregion

        #region Confirmar Alterar Email
        [HttpGet]
        public async Task<IActionResult> ConfirmarAlterarEmailAsync([FromServices] UsuarioApp usuarioApp, string userId, string email, string code)
        {
            if (userId == null || code == null)
            {
                return View(new StatusMessageViewModel("Erro ao alterar seu e-mail."));
            }
            bool result = await usuarioApp.ConfirmarAlterarEmail(userId, email, code);
            StatusMessageViewModel statusMessageViewModel;
            if (result)
                statusMessageViewModel = new StatusMessageViewModel("Obrigado por confirmar seu a alteração de seu e-mail.");
            else
                statusMessageViewModel = new StatusMessageViewModel("Erro ao alterar seu e-mail.");

            return View(statusMessageViewModel);
        }
        #endregion
    }
}