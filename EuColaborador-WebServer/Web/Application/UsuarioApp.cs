using CrossCutting.Utils;
using Domain.DTO;
using Microsoft.AspNetCore.Http;
using System;
using System.Net.Http;
using System.Threading.Tasks;
using Web.Application.Util;
using Web.Models;

namespace Web.Application
{
    public class UsuarioApp
    {
        public async Task<HttpResponseMessage> CadastrarUsuario(HttpContext context, UsuarioInputModel model)
        {
            HttpClient httpClient = new HttpClient();
            ApiRequest.InserirToken(context, httpClient);
            HttpResponseMessage httpResponseMessage = await httpClient.PostAsync(ApiEndPoint.CadastrarUsuarioAdmin, model.ToStringContent());
            return httpResponseMessage;
        }

        public async Task<bool> Login(HttpContext httpContext, LoginUsuarioInputModel model)
        {
            HttpClient httpClient = new HttpClient();
            HttpResponseMessage httpResponseMessage = await httpClient.PostAsync(ApiEndPoint.Login, model.ToStringContent());
            if (httpResponseMessage.IsSuccessStatusCode)
            {
                string response = await httpResponseMessage.Content.ReadAsStringAsync();
                httpContext.Session.SetString("Usuario", response);
            }
            return httpResponseMessage.IsSuccessStatusCode;
        }

        public async Task<bool> RecuperarSenha(RecuperarSenhaInputModel model)
        {
            HttpClient httpClient = new HttpClient();
            HttpResponseMessage httpResponseMessage = await httpClient.PostAsync(ApiEndPoint.RecuperarSenha, model.ToStringContent());
            return httpResponseMessage.IsSuccessStatusCode;
        }

        public async Task<bool> ResetarSenha(ResetSenhaInputModel model)
        {
            HttpClient httpClient = new HttpClient();
            HttpResponseMessage httpResponseMessage = await httpClient.PostAsync(ApiEndPoint.ResetSenha, model.ToStringContent());
            return httpResponseMessage.IsSuccessStatusCode;
        }

        public async Task<bool> ConfirmarEmail(string userId, string code)
        {
            HttpClient httpClient = new HttpClient();
            HttpResponseMessage httpResponseMessage = await httpClient.GetAsync(ApiEndPoint.ConfirmarEmail + "?userId=" + userId + "&code=" + code);
            return httpResponseMessage.IsSuccessStatusCode;
        }

        public async Task<bool> ConfirmarAlterarEmail(string userId, string email, string code)
        {
            HttpClient httpClient = new HttpClient();
            HttpResponseMessage httpResponseMessage = await httpClient.GetAsync(ApiEndPoint.ConfirmarAlterarEmail + "?userId=" + userId + "&email=" + email + "&code=" + code);
            return httpResponseMessage.IsSuccessStatusCode;
        }
    }
}
