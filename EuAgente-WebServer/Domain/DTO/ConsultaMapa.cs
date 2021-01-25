using System;
using System.Collections.Generic;

namespace Domain.DTO
{
    public class ConsultaMapa
    {
        public DateTime DataInicial { get; set; }
        public DateTime DataFinal { get; set; }
        public List<Posicao> Posicoes { get; set; }

        public ConsultaMapa()
        {
        }

        public ConsultaMapa(DateTime dataInicial, DateTime dataFinal, List<Posicao> posicoes)
        {
            DataInicial = dataInicial;
            DataFinal = dataFinal;
            Posicoes = posicoes;
        }
    }
}
