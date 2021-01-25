using CrossCutting.Utils;
using Domain.DTO;
using Microsoft.AspNetCore.Http;
using System.Net.Http;
using System.Threading.Tasks;
using Web.Application.Util;
using Web.Models;

namespace Web.Application
{
    public class GerenciarPerfilUsuarioApp
    {
        public async Task<bool> AtualizarPerfil(HttpContext context, AtualizarUsuarioInputModel model)
        {
            HttpClient httpClient = new HttpClient();
            ApiRequest.InserirToken(context, httpClient);
            HttpResponseMessage httpResponseMessage = await httpClient.PutAsync(ApiEndPoint.AtualizarPerfil, model.ToStringContent());
            if (httpResponseMessage.IsSuccessStatusCode)
            {
                string response = await httpResponseMessage.Content.ReadAsStringAsync();
                context.Session.SetString("Usuario", response);
            }
            return httpResponseMessage.IsSuccessStatusCode;
        }

        public async Task<bool> AlterarEmail(HttpContext context, AlterarEmailInputModel model)
        {
            HttpClient httpClient = new HttpClient();
            ApiRequest.InserirToken(context, httpClient);
            HttpResponseMessage httpResponseMessage = await httpClient.PostAsync(ApiEndPoint.AlterarEmail, model.ToStringContent());
            return httpResponseMessage.IsSuccessStatusCode;
        }


        public async Task<bool> AlterarSenha(HttpContext context, AlterarSenhaInputModel model)
        {
            HttpClient httpClient = new HttpClient();
            ApiRequest.InserirToken(context, httpClient);
            HttpResponseMessage httpResponseMessage = await httpClient.PostAsync(ApiEndPoint.AlterarSenha, model.ToStringContent());
            return httpResponseMessage.IsSuccessStatusCode;
        }

        public async Task<bool> EnviarEmailConfirmacao(HttpContext context, string email)
        {
            HttpClient httpClient = new HttpClient();
            ApiRequest.InserirToken(context, httpClient);
            HttpResponseMessage httpResponseMessage = await httpClient.GetAsync(ApiEndPoint.EnviarEmailConfirmacao + "?email=" + email);
            return httpResponseMessage.IsSuccessStatusCode;
        }
    }
}
