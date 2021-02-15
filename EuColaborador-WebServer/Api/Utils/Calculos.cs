using System;

namespace Api.Utils
{
    public static class Calculos
    {
        public static int CalcularIdade(DateTime nascimento, DateTime dataCalculo)
        {
            var idade = dataCalculo.Year - nascimento.Year;
            if (nascimento.DayOfYear < dataCalculo.DayOfYear)
                idade--;
            return idade;
        }
    }
}
