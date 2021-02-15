using Domain.Entities;
using System;

namespace Domain.DTO
{
    public class LoginUsarioOutputModel
    {
        public UsuarioOutputModel Usuario { get; set; }
        public string Token { get; set; }

        public LoginUsarioOutputModel(UsuarioOutputModel usuario, string token)
        {
            Usuario = usuario;
            Token = token;
        }
    }

    public class UsuarioOutputModel
    {
        public string Id { get; set; }
        public string Nome { get; set; }
        public string Login { get; set; }
        public string CPF { get; set; }
        public string Email { get; set; }
        public DateTime DataNascimento { get; set; }
        public char Sexo { get; set; }
        public bool EmailConfirmado { get; set; }

        public UsuarioOutputModel()
        {
        }

        public UsuarioOutputModel(Usuario user)
        {
            Id = user.Id;
            Nome = user.Pessoa.Nome;
            CPF = user.UserName;
            Email = user.Email;
            Login = user.UserName;
            CPF = user.Pessoa.Cpf;
            DataNascimento = user.Pessoa.DataNascimento;
            Sexo = user.Pessoa.Sexo;
            EmailConfirmado = user.EmailConfirmed;
        }
    }
}
