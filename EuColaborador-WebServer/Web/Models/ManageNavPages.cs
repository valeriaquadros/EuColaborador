using Microsoft.AspNetCore.Mvc.Rendering;
using System;

namespace Web.Models
{
    public static class ManageNavPages
    {
        public static string Perfil => "Perfil";

        public static string Email => "Email";

        public static string AlterarSenha => "AlterarSenha";

        public static string PerfilNavClass(ViewContext viewContext) => PageNavClass(viewContext, Perfil);

        public static string EmailNavClass(ViewContext viewContext) => PageNavClass(viewContext, Email);

        public static string AlterarSenhaNavClass(ViewContext viewContext) => PageNavClass(viewContext, AlterarSenha);

        private static string PageNavClass(ViewContext viewContext, string page)
        {
            var activePage = viewContext.ViewData["ActivePage"] as string
                ?? System.IO.Path.GetFileNameWithoutExtension(viewContext.ActionDescriptor.DisplayName);
            return string.Equals(activePage, page, StringComparison.OrdinalIgnoreCase) ? "active" : null;
        }
    }
}
