using CrossCutting.Utils;
using System;
using System.ComponentModel.DataAnnotations;

namespace Domain.DTO
{
    public class UsuarioInputModel
    {
        private string cPF;

        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        public string Nome { get; set; }

        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        [Cpf(ErrorMessage = "CPF inválido")]
        public string CPF
        {
            get => cPF;
            set
            {
                if (!string.IsNullOrWhiteSpace(value))
                    cPF = value.Trim().Replace(".", "").Replace("-", "");
            }
        }

        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        [EmailAddress(ErrorMessage = "O campo {0} está em formato inválido")]
        public string Email { get; set; }

        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        [Display(Name = "Senha")]
        [DataType(DataType.Password)]
        [MinLength(6, ErrorMessage = "O tamanho mínimo para senha é de 6 caracteres")]
        public string Password { get; set; }

        [DataType(DataType.Password)]
        [Compare("Password", ErrorMessage = "A senha e a confirmação de senham não conferem.")]
        [Display(Name = "Confirmação de senha")]
        public string ConfirmacaoSenha { get; set; }

        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        [Display(Name = "Data de nascimento")]
        [DataType(DataType.Date)]
        public DateTime DataNascimento { get; set; }

        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        public char Sexo { get; set; }
    }

    public class AtualizarUsuarioInputModel
    {
        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        public string Nome { get; set; }

        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        [Display(Name = "Data de nascimento")]
        [DataType(DataType.Date)]
        public DateTime DataNascimento { get; set; }

        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        public char Sexo { get; set; }
    }

    public class LoginUsuarioInputModel
    {
        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        public string Login { get; set; }
        [DataType(DataType.Password)]

        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        public string Password { get; set; }

        public LoginUsuarioInputModel()
        {
        }

        public LoginUsuarioInputModel(string login, string password)
        {
            Login = login;
            Password = password;
        }
    }

    public class RecuperarSenhaInputModel
    {
        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        [EmailAddress]
        public string Email { get; set; }
    }

    public class ResetSenhaInputModel
    {
        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        [EmailAddress]
        public string Email { get; set; }

        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        [Display(Name = "Senha")]
        [StringLength(100, ErrorMessage = "A senha deve possuir no mínimo 6 caracteres.", MinimumLength = 6)]
        [DataType(DataType.Password)]
        public string Senha { get; set; }

        [Display(Name = "Confirmação de senha")]
        [DataType(DataType.Password)]
        [Compare("Senha", ErrorMessage = "A senha e a confirmação de senham não conferem.")]
        public string ConfirmacaoSenha { get; set; }

        public string Code { get; set; }
    }

    public class AlterarSenhaInputModel
    {
        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        [DataType(DataType.Password)]
        [Display(Name = "Senha antiga")]
        public string SenhaAntiga { get; set; }

        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        [StringLength(100, ErrorMessage = "A senha deve possuir no mínimo 6 caracteres.", MinimumLength = 6)]
        [DataType(DataType.Password)]
        [Display(Name = "Nova senha")]
        public string NovaSenha { get; set; }

        [DataType(DataType.Password)]
        [Compare("NovaSenha", ErrorMessage = "A senha e a confirmação de senham não conferem.")]
        [Display(Name = "Confirmação de senha")]
        public string ConfirmacaoSenha { get; set; }
    }

    public class AlterarEmailInputModel
    {
        [Required(ErrorMessage = "O campo {0} é obrigatório")]
        [EmailAddress]
        [Display(Name = "Novo email")]
        public string NovoEmail { get; set; }

        public AlterarEmailInputModel()
        {
        }

        public AlterarEmailInputModel(string novoEmail)
        {
            NovoEmail = novoEmail;
        }
    }

    public class EmailInputModel
    {
        [EmailAddress]
        public string Email { get; set; }

        [EmailAddress]
        [Display(Name = "Novo email")]
        public string NovoEmail { get; set; }

        public bool EmailConfirmado { get; set; }
    }
}
