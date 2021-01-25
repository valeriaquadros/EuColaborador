using Domain.Interfaces;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Domain.Entities
{
    public class Enfermo : Entidade, IReportavel
    {
        [Required]
        [ForeignKey("Endemia")]
        public int EndemiaId { get; set; }
        public virtual Endemia Endemia { get; set; }
        [Required]
        [ForeignKey("Pessoa")]
        public int PessoaId { get; set; }
        public virtual Pessoa Pessoa { get; set; }
        [Required]
        public bool Gestante { get; set; }
        [Required]
        public DateTime Data { get; set; }
        public virtual List<EnfermoEndereco> EnfermoEndereco { get; set; }
        [NotMapped]
        public List<int> Enderecos { get; set; }
        [NotMapped]
        public int Idade { get; set; }
    }
}
