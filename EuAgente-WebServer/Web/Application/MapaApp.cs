using Domain.DTO;
using Newtonsoft.Json;
using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Web.Models;

namespace Web.Application
{
    public class MapaApp
    {
        public async Task<ConsultaMapa> GetPosicoesAsync()
        {
            return await GetPosicoesAsync(null, null);
        }

        public async Task<ConsultaMapa> GetPosicoesAsync(DateTime? dataInicial, DateTime? dataFinal)
        {
            HttpClient httpClient = new HttpClient();
            StringBuilder url = new StringBuilder(ApiEndPoint.LocalizacoesMapa);

            if (dataInicial != null && dataFinal != null)
                url.Append("?dataInicial=")
                    .Append(dataInicial)
                    .Append("&dataFinal=")
                    .Append(dataFinal);

            HttpResponseMessage httpResponseMessage = await httpClient.GetAsync(url.ToString());
            if (httpResponseMessage.IsSuccessStatusCode)
            {
                string response = await httpResponseMessage.Content.ReadAsStringAsync();
                return JsonConvert.DeserializeObject<ConsultaMapa>(response);
            }
            else
            {
                return null;
            }
        }
    }
}
