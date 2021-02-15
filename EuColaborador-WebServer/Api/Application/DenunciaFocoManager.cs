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
    public class DenunciaFocoManager : RelatorioManager
    {
        private readonly CustomContext _context;

        public DenunciaFocoManager(CustomContext context)
        {
            _context = context;
        }

        public override ExcelPackage GerarRelatorioExcel(DateTime? dataInicial, DateTime? dataFinal)
        {
            var tabelaQuery = _context.DenunciaFoco.AsQueryable();
            ICollection<IReportavel> entidades = FiltrarPorData(dataInicial, dataFinal, tabelaQuery);

            var tabela = new DataTable("Denuncia Foco");
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
                { "Logradouro", typeof(string) },
                { "Numero", typeof(string) },
                { "Cep", typeof(string) },
                { "Bairro", typeof(string) },
                { "Cidade", typeof(string) },
                { "Estado", typeof(string) },
                { "Latitude", typeof(string) },
                { "Longitude", typeof(string) }
            };
        }

        protected override void AdicionarDadosExcel(DataTable tabela, ICollection<IReportavel> entidades)
        {
            foreach (DenunciaFoco denunciaFoco in entidades.OfType<DenunciaFoco>())
            {
                DataRow row = tabela.NewRow();
                row["Id"] = denunciaFoco.Id;
                row["Data Registro"] = denunciaFoco.Data;
                row["Logradouro"] = denunciaFoco.Endereco.Logradouro;
                row["Numero"] = denunciaFoco.Endereco.Numero;
                row["Cep"] = denunciaFoco.Endereco.CEP;
                row["Bairro"] = denunciaFoco.Endereco.Bairro;
                row["Cidade"] = denunciaFoco.Endereco.Cidade;
                row["Estado"] = denunciaFoco.Endereco.Estado;
                row["Latitude"] = denunciaFoco.Endereco.Latitude;
                row["Longitude"] = denunciaFoco.Endereco.Longitude;
                tabela.Rows.Add(row);
            }
        }
    }
}
