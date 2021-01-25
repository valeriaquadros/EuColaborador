using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace Domain.Entities
{
    public class TipoFoco : Entidade
    {
        [Required]
        [ForeignKey("Endemia")]
        public int EndemiaId { get; set; }
        [JsonIgnore]
        public virtual Endemia Endemia { get; set; }
        [Required]
        public string Descricao { get; set; }
    }
}
