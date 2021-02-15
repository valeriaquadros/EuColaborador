using Domain.Interfaces;
using OfficeOpenXml;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;

namespace Api.Application
{
    public abstract class RelatorioManager
    {
        protected ICollection<DataTable> Tabelas { get; set; }

        protected RelatorioManager()
        {
            Tabelas = new List<DataTable>();
        }

        public abstract ExcelPackage GerarRelatorioExcel(DateTime? dataInicial, DateTime? dataFinal);

        protected abstract void AdicionarDadosExcel(DataTable tabela, ICollection<IReportavel> entidades);

        protected ICollection<IReportavel> FiltrarPorData(DateTime? dataInicial, DateTime? dataFinal, IQueryable<IReportavel> tabelaQuery)
        {
            ICollection<IReportavel> entidades;
            if (dataInicial == null && dataFinal == null)
            {
                entidades = tabelaQuery.ToList();
            }
            else if (dataInicial == null)
            {
                entidades = tabelaQuery.Where(e => e.Data <= dataFinal).ToList();
            }
            else if (dataFinal == null)
            {
                entidades = tabelaQuery.Where(e => e.Data >= dataInicial).ToList();
            }
            else
            {
                entidades = tabelaQuery.Where(e => e.Data >= dataInicial && e.Data <= dataFinal).ToList();
            }

            return entidades;
        }

        protected void AdicionarColunasExcel(DataTable dataTable, Dictionary<string, Type> colunas)
        {
            foreach (var coluna in colunas)
            {
                dataTable.Columns.Add(coluna.Key, coluna.Value);
            }
        }

        protected ExcelPackage GerarArquivoExcel()
        {
            var package = new ExcelPackage();
            foreach (var dataTable in Tabelas)
            {
                var worksheet = package.Workbook.Worksheets.Add(dataTable.TableName);
                worksheet.Cells["A1"].LoadFromDataTable(dataTable, PrintHeaders: true);

                for (int linha = 0; linha < dataTable.Rows.Count; linha++)
                {
                    for (int coluna = 0; coluna < dataTable.Columns.Count; coluna++)
                    {
                        FormatarDadosPlanilha(worksheet, linha + 2, coluna + 1, dataTable.Rows[linha], dataTable.Columns[coluna]);
                    }
                }
            }
            return package;
        }

        protected void FormatarDadosPlanilha(ExcelWorksheet worksheet, int linha, int coluna, DataRow r, DataColumn dc)
        {
            if (dc.DataType == typeof(DateTime))
            {
                if (!r.IsNull(dc))
                {
                    worksheet.SetValue(linha, coluna, (DateTime)r[dc]);
                    var dtFormat = "dd/MM/yyyy";
                    worksheet.Cells[linha, coluna].Style.Numberformat.Format = dtFormat;
                }
                else worksheet.SetValue(linha, coluna, null);
            }
            else
                worksheet.SetValue(linha, coluna, !r.IsNull(dc) ? Convert.ChangeType(r[dc].ToString(), dc.DataType) : null);
        }
    }
}
