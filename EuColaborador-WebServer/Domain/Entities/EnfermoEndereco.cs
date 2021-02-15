using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace Domain.Entities
{
    public class EnfermoEndereco : Entidade
    {
        [ForeignKey("Enfermo")]
        public int EnfermoId { get; set; }
        [JsonIgnore]
        public virtual Enfermo Enfermo { get; set; }
        [ForeignKey("Endereco")]
        public int EnderecoId { get; set; }
        public virtual Endereco Endereco { get; set; }
    }
}
