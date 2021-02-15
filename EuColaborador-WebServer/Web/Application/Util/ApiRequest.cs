using Domain.DTO;
using Microsoft.AspNetCore.Http;
using System.Net.Http;
using System.Net.Http.Headers;

namespace Web.Application.Util
{
    public static class ApiRequest
    {
        public static void InserirToken(HttpContext context, HttpClient httpClient)
        {
            httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", context.Session.Get<LoginUsarioOutputModel>("Usuario").Token);
        }

    }
}
