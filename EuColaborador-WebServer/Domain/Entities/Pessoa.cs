using CrossCutting.Utils;
using Domain.DTO;
using System;
using System.ComponentModel.DataAnnotations;

namespace Domain.Entities
{
    public class Pessoa : Entidade
    {
        public Pessoa()
        {
        }

        public Pessoa(UsuarioInputModel registerUser)
        {
            Nome = registerUser.Nome;
            Cpf = registerUser.CPF;
            DataNascimento = registerUser.DataNascimento;
            Sexo = registerUser.Sexo;
        }

        [Required]
        public string Nome { get; set; }
        [Cpf]
        [Required]
        public string Cpf { get; set; }
        [Required]
        public DateTime DataNascimento { get; set; }
        [Required]
        public char Sexo { get; set; }

    }
}
