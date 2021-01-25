using Domain.DTO;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Threading.Tasks;
using Web.Application;

namespace Web.Controllers
{
    public class MapaController : Controller
    {
        public async Task<IActionResult> Index([FromServices] MapaApp mapaApplication, string latitude = "null", string longitude = "null")
        {
            ViewBag.ApiKey = Environment.GetEnvironmentVariable("BINGAPIKEY");
            ViewBag.Latitude = latitude;
            ViewBag.Longitude = longitude;
            ConsultaMapa consultaMapa = await mapaApplication.GetPosicoesAsync();
            return View(consultaMapa);
        }

        public async Task<ActionResult<ConsultaMapa>> BuscarPosicoes([FromServices] MapaApp mapaApplication, DateTime? dataInicial, DateTime? dataFinal)
        {
            if (dataFinal < dataInicial)
                return BadRequest("A data inicial não pode ser maior que a data final.");
            
            return await mapaApplication.GetPosicoesAsync(dataInicial, dataFinal);
        }
    }
}