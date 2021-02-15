using Api.Utils;
using Data.Contexts;
using Domain.DTO;
using Domain.Entities;
using Domain.Interfaces;
using OfficeOpenXml;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;

namespace Api.Application
{
    public class EnfermoManager : RelatorioManager
    {
        private readonly CustomContext _context;

        public EnfermoManager(CustomContext context)
        {
            _context = context;
        }

        public bool CadastradoUltimaSemana(Enfermo enfermo)
        {
            return _context.Enfermo
                .Where(x =>
                    x.PessoaId == enfermo.PessoaId
                    && (enfermo.Data - x.Data) < new TimeSpan(7, 0, 0, 0))
                .ToList().Count > 0;
        }

        #region ConsultaMapa
        public ConsultaMapa BuscarConsultaMapa(DateTime? dataInicial, DateTime? dataFinal)
        {
            ValidarDatas(ref dataInicial, ref dataFinal);

            List<Posicao> posicoes = BuscarPosicoes(dataInicial, dataFinal);

            return new ConsultaMapa((DateTime)dataInicial, (DateTime)dataFinal, posicoes);
        }

        /// <summary>
        /// Caso não seja informado data, será buscado o ano epidemiológico atual
        /// O ano epidemiológico começa no domingo da semana do dia 01 de janeiro,
        /// ou seja, caso dia 1 seja domingo, ele mesmo, se não, é o último domingo
        /// do ano anterior
        /// </summary>
        /// <param name="dataInicial"></param>
        /// <param name="dataFinal"></param>
        private static void ValidarDatas(ref DateTime? dataInicial, ref DateTime? dataFinal)
        {
            if (dataInicial == null || dataFinal == null)
            {
                DateTime primeiroDiaAno = new DateTime(DateTime.Today.Year, 1, 1);
                int diasPrimeiraSemana = 7 - (int)primeiroDiaAno.DayOfWeek;

                if (primeiroDiaAno.DayOfWeek == DayOfWeek.Sunday)
                    dataInicial = primeiroDiaAno;
                else if (diasPrimeiraSemana < 4)
                    dataInicial = primeiroDiaAno.AddDays(diasPrimeiraSemana);
                else
                {
                    DateTime ultimoDiaAno = new DateTime(DateTime.Today.Year - 1, 12, 31);
                    dataInicial = ultimoDiaAno.AddDays(-(int)ultimoDiaAno.DayOfWeek);
                }
                dataFinal = DateTime.Now;
            }
            else
            {
                dataFinal=((DateTime)dataFinal).AddDays(1).AddSeconds(-1); //Altera o horário para o final do dia
            }
        }

        private List<Posicao> BuscarPosicoes(DateTime? dataInicial, DateTime? dataFinal)
        {
            return _context.EnfermoEndereco
                .Where(x => x.Endereco.Latitude != null && x.Enfermo.Data >= dataInicial && x.Enfermo.Data <= dataFinal)
                .Select(x => new Posicao(x.Endereco.Latitude, x.Endereco.Longitude, x.Enfermo.Data))
                .ToList();
        }

        #endregion

        #region Relatório Excel
        public override ExcelPackage GerarRelatorioExcel(DateTime? dataInicial, DateTime? dataFinal)
        {
            var tabelaQuery = _context.Enfermo.AsQueryable();
            ICollection<IReportavel> entidades = FiltrarPorData(dataInicial, dataFinal, tabelaQuery);

            var tabela = new DataTable("Enfermos");
            Tabelas.Add(tabela);
            Dictionary<string, Type> colunas = DefinirColunas();

            AdicionarColunasExcel(tabela, colunas);
            AdicionarDadosExcel(tabela, entidades);
            return GerarArquivoExcel();
        }

        protected Dictionary<string, Type> DefinirColunas()
        {
            return new Dictionary<string, Type>
            {
                { "Pessoa Id", typeof(int) },
                { "Idade no registro", typeof(int) },
                { "Sexo", typeof(string) },
                { "Gestante", typeof(string) },
                { "Data Registro", typeof(DateTime) },
                { "Bairro", typeof(string) },
                { "Cidade", typeof(string) },
                { "Estado", typeof(string) }
            };
        }

        protected override void AdicionarDadosExcel(DataTable tabela, ICollection<IReportavel> entidades)
        {
            foreach (Enfermo enfermo in entidades.OfType<Enfermo>())
            {
                enfermo.Idade = Calculos.CalcularIdade(enfermo.Pessoa.DataNascimento, enfermo.Data);

                foreach (var enfermoEndereco in enfermo.EnfermoEndereco)
                {
                    DataRow row = tabela.NewRow();
                    row["Pessoa Id"] = enfermo.PessoaId;
                    row["Idade no registro"] = enfermo.Idade;
                    row["Sexo"] = enfermo.Pessoa.Sexo;
                    row["Gestante"] = enfermo.Gestante ? "Sim" : "Não";
                    row["Data Registro"] = enfermo.Data;
                    row["Bairro"] = enfermoEndereco.Endereco.Bairro;
                    row["Cidade"] = enfermoEndereco.Endereco.Cidade;
                    row["Estado"] = enfermoEndereco.Endereco.Estado;
                    tabela.Rows.Add(row);
                }
            }
        }
        #endregion
    }
}
