using System.ComponentModel.DataAnnotations;

namespace CrossCutting.Utils
{
    /// <summary>
    /// Verifica se o campo é um CPF válido
    /// </summary>
    public class CpfAttribute : ValidationAttribute
    {
        public CpfAttribute()
        {
        }

        protected override ValidationResult IsValid(
            object value, ValidationContext validationContext)
        {
            if (value == null || !ValidaCpf.IsCpf(value.ToString()))
            {
                return new ValidationResult("CPF inválido");
            }

            return ValidationResult.Success;
        }
    }
}
