using Data.Contexts;
using Domain.Entities;
using Domain.Interfaces;
using OfficeOpenXml;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;

namespace Api.Application
{
    public class ChecklistEnderecoManager : RelatorioManager
    {
        private readonly CustomContext _context;

        public ChecklistEnderecoManager(CustomContext context)
        {
            _context = context;
        }

        public override ExcelPackage GerarRelatorioExcel(DateTime? dataInicial, DateTime? dataFinal)
        {
            var tabelaQuery = _context.ChecklistEndereco.AsQueryable();
            ICollection<IReportavel> entidades = FiltrarPorData(dataInicial, dataFinal, tabelaQuery);

            var tabela = new DataTable("ChecklistEndereco");
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
                { "Id", typeof(int) },
                { "Data Registro", typeof(DateTime) },
                { "Bairro", typeof(string) },
                { "Cidade", typeof(string) },
                { "Estado", typeof(string) },
                { "Id Tipo Foco", typeof(string) },
                { "Descrição Tipo Foco", typeof(string) },
            };
        }

        protected override void AdicionarDadosExcel(DataTable tabela, ICollection<IReportavel> entidades)
        {
            foreach (ChecklistEndereco checklistEndereco in entidades.OfType<ChecklistEndereco>())
            {
                DataRow row = tabela.NewRow();
                row["Id"] = checklistEndereco.Id;
                row["Data Registro"] = checklistEndereco.Data;
                row["Bairro"] = checklistEndereco.Endereco.Bairro;
                row["Cidade"] = checklistEndereco.Endereco.Cidade;
                row["Estado"] = checklistEndereco.Endereco.Estado;
                row["Id Tipo Foco"] = checklistEndereco.TipoFocoId;
                row["Descrição Tipo Foco"] = checklistEndereco.TipoFoco.Descricao;
                tabela.Rows.Add(row);
            }
        }
    }
}
