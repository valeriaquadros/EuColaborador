using Domain.DTO;
using Microsoft.AspNetCore.Identity;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Domain.Entities
{
    public class Usuario : IdentityUser
    {
        [Required]
        [ForeignKey("Pessoa")]
        public int PessoaId { get; set; }
        public virtual Pessoa Pessoa { get; set; }

        public Usuario()
        {
        }

        public Usuario(UsuarioInputModel registerUser)
        {
            UserName = registerUser.CPF;
            Pessoa = new Pessoa(registerUser);
            Email = registerUser.Email;
            EmailConfirmed = false;
        }

        public Usuario(UsuarioInputModel registerUser, Pessoa pessoa)
        {
            UserName = registerUser.CPF;
            Pessoa = pessoa;
            Email = registerUser.Email;
            EmailConfirmed = false;
        }

        public void UpdateUser(AtualizarUsuarioInputModel input)
        {
            Pessoa.Nome = input.Nome;
            Pessoa.DataNascimento = input.DataNascimento;
            Pessoa.Sexo = input.Sexo;
        }
    }
}
