using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Domain.Entities
{
    public class Endemia : Entidade
    {
        [Required]
        public string Nome { get; set; }
        public virtual List<TipoFoco> TiposFoco { get; set; }
    }
}
