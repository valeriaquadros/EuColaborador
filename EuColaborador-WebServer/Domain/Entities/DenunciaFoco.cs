using Domain.Interfaces;
using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Domain.Entities
{
    public class DenunciaFoco : Entidade, IReportavel
    {
        [Required]
        [ForeignKey("Endereco")]
        public int EnderecoId { get; set; }
        public virtual Endereco Endereco { get; set; }
        [Required]
        [ForeignKey("Endemia")]
        public int EndemiaId { get; set; }
        public virtual Endemia Endemia { get; set; }
        public DateTime Data { get; set; }
    }
}
