using CrossCutting.Constants;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.IO;
using System.Net.Http;
using System.Threading.Tasks;
using Web.Application;

namespace Web.Controllers
{
    public class RelatorioController : Controller
    {
        [Authorize(Roles = Perfis.Administrador)]
        public async Task<IActionResult> ChecklistEndereco([FromServices] RelatorioApp relatorioApp)
        {
            var response = await relatorioApp.GetRelatorioChecklistEndereco(HttpContext);
            
            if (response.IsSuccessStatusCode)
            {
                Response.Cookies.Append("ExcelDownloadFlag", "Flag", new CookieOptions { Expires = DateTime.Now.AddDays(1), IsEssential = true });
                HttpContent content = response.Content;
                Stream contentStream = await content.ReadAsStreamAsync();
                return File(contentStream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "RelatorioChecklistEndereco.xlsx");
            }
            else
            {
                throw new FileNotFoundException();
            }
        }

        [Authorize(Roles = Perfis.Administrador)]
        public async Task<IActionResult> DenunciaFoco([FromServices] RelatorioApp relatorioApp)
        {
            var response = await relatorioApp.GetRelatorioDenunciaFoco(HttpContext);

            if (response.IsSuccessStatusCode)
            {
                Response.Cookies.Append("ExcelDownloadFlag", "Flag", new CookieOptions { Expires = DateTime.Now.AddDays(1), IsEssential = true });
                HttpContent content = response.Content;
                var contentStream = await content.ReadAsStreamAsync();
                return File(contentStream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "RelatorioDenunciaFoco.xlsx");
            }
            else
            {
                throw new FileNotFoundException();
            }
        }

        [Authorize(Roles = Perfis.Administrador)]
        public async Task<IActionResult> Enfermo([FromServices] RelatorioApp relatorioApp)
        {
            var response = await relatorioApp.GetRelatorioEnfermo(HttpContext);

            if (response.IsSuccessStatusCode)
            {
                Response.Cookies.Append("ExcelDownloadFlag", "Flag", new CookieOptions { Expires = DateTime.Now.AddDays(1), IsEssential = true });
                HttpContent content = response.Content;
                var contentStream = await content.ReadAsStreamAsync();
                return File(contentStream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "RelatorioEnfermo.xlsx");
            }
            else
            {
                throw new FileNotFoundException();
            }
        }
    }
}
