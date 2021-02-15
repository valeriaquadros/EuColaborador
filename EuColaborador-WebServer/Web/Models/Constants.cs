using System;

namespace Web.Models
{
    public static class ApiEndPoint
    {
        public static string ApiUrl => Environment.GetEnvironmentVariable("APIURL");
        #region Usuario
        #region Geral
        public static string ConfirmarAlterarEmail => string.Concat(ApiUrl, "/api/Usuario/ConfirmarAlterarEmail");
        public static string ConfirmarEmail => string.Concat(ApiUrl, "/api/Usuario/ConfimarEmail");
        public static string Login => string.Concat(ApiUrl, "/api/Usuario/login");
        public static string RecuperarSenha => string.Concat(ApiUrl, "/api/Usuario/recuperarsenha");
        public static string ResetSenha => string.Concat(ApiUrl, "/api/Usuario/resetarsenha");
        #endregion

        #region Perfil
        public static string AlterarEmail => string.Concat(ApiUrl, "/api/Usuario/AlterarEmail");
        public static string AlterarSenha => string.Concat(ApiUrl, "/api/Usuario/AlterarSenha");
        public static string AtualizarPerfil => string.Concat(ApiUrl, "/api/Usuario");
        public static string EnviarEmailConfirmacao => string.Concat(ApiUrl, "/api/Usuario/EnviarEmailConfirmacao");
        #endregion

        public static string CadastrarUsuarioAdmin => string.Concat(ApiUrl, "/api/Usuario/CadastrarAdmin");
        #endregion

        #region Mapas
        public static string LocalizacoesMapa => string.Concat(ApiUrl, "/api/Enfermo/localizacoes");
        #endregion

        #region Relatorios
        public static string RelatorioChecklistEndereco => string.Concat(ApiUrl, "/api/ChecklistEndereco/gerarrelatorio");
        public static string RelatorioDenunciaFoco => string.Concat(ApiUrl, "/api/DenunciaFoco/gerarrelatorio");
        public static string RelatorioEnfermo => string.Concat(ApiUrl, "/api/Enfermo/gerarrelatorio");
        #endregion
    }
}
