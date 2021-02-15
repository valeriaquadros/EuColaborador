using Domain.Interfaces;
using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Domain.Entities
{
    public class ChecklistEndereco : Entidade, IReportavel
    {
        [Required]
        [ForeignKey("TipoFoco")]
        public int TipoFocoId { get; set; }
        public virtual TipoFoco TipoFoco { get; set; }
        [Required]
        [ForeignKey("Endereco")]
        public int EnderecoId { get; set; }
        public virtual Endereco Endereco { get; set; }
        public DateTime Data { get; set; }
    }
}
