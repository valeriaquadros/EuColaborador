using Microsoft.AspNetCore.Http;
using System.Net.Http;
using System.Threading.Tasks;
using Web.Application.Util;
using Web.Models;

namespace Web.Application
{
    public class RelatorioApp
    {
        public async Task<HttpResponseMessage> GetRelatorioChecklistEndereco(HttpContext context)
        {
            HttpClient httpClient = new HttpClient();
            ApiRequest.InserirToken(context, httpClient);
            return await httpClient.GetAsync(ApiEndPoint.RelatorioChecklistEndereco);
        }

        public async Task<HttpResponseMessage> GetRelatorioDenunciaFoco(HttpContext context)
        {
            HttpClient httpClient = new HttpClient();
            ApiRequest.InserirToken(context, httpClient);
            return await httpClient.GetAsync(ApiEndPoint.RelatorioDenunciaFoco);
        }

        public async Task<HttpResponseMessage> GetRelatorioEnfermo(HttpContext context)
        {
            HttpClient httpClient = new HttpClient();
            ApiRequest.InserirToken(context, httpClient);
            return await httpClient.GetAsync(ApiEndPoint.RelatorioEnfermo);
        }
    }
}
