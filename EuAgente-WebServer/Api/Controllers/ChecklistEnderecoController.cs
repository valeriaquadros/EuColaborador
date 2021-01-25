using Api.Application;
using CrossCutting.Constants;
using Data.Contexts;
using Domain.DTO;
using Domain.Entities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Api.Controllers
{
    [Route("api/[controller]")]
    [Authorize]
    [ApiController]
    public class ChecklistEnderecoController : ControllerBase
    {
        private readonly CustomContext _context;

        public ChecklistEnderecoController(CustomContext context)
        {
            _context = context;
        }

        // GET: api/ChecklistEndereco
        [HttpGet]
        public async Task<ActionResult<IEnumerable<ChecklistEndereco>>> GetChecklistEndereco()
        {
            return await _context.ChecklistEndereco.ToListAsync();
        }

        // GET: api/ChecklistEndereco/5
        [HttpGet("{id}")]
        public async Task<ActionResult<ChecklistEndereco>> GetChecklistEndereco(int id)
        {
            var checklistEndereco = await _context.ChecklistEndereco.FindAsync(id);

            if (checklistEndereco == null)
            {
                return NotFound();
            }

            return checklistEndereco;
        }

        // GET: api/ChecklistEndereco/5
        [HttpGet("{enderecoId}/{data}")]
        public ActionResult<IEnumerable<ChecklistEndereco>> GetChecklistEndereco(int enderecoId, DateTime data)
        {
            var checklistEndereco = _context.ChecklistEndereco.Where(x => x.EnderecoId == enderecoId && x.Data == data).ToList();

            if (checklistEndereco == null)
            {
                return NotFound();
            }

            return checklistEndereco;
        }

        // PUT: api/ChecklistEndereco/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutChecklistEndereco(int id, ChecklistEndereco checklistEndereco)
        {
            if (id != checklistEndereco.Id)
            {
                return BadRequest();
            }

            _context.Entry(checklistEndereco).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ChecklistEnderecoExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/ChecklistEndereco
        [HttpPost]
        public async Task<ActionResult<IEnumerable<ChecklistEndereco>>> PostChecklistEnderecoList(ChecklistEnderecoInputModel checklistEndereco)
        {
            _context.ChecklistEndereco.AddRange(checklistEndereco.ChecklistEndereco);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetChecklistEndereco",
                                   new
                                   {
                                       enderecoId = checklistEndereco.ChecklistEndereco.First().EnderecoId,
                                       data = checklistEndereco.ChecklistEndereco.First().Data
                                   },
                                   checklistEndereco);
        }

        // DELETE: api/ChecklistEndereco/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<ChecklistEndereco>> DeleteChecklistEndereco(int id)
        {
            var checklistEndereco = await _context.ChecklistEndereco.FindAsync(id);
            if (checklistEndereco == null)
            {
                return NotFound();
            }

            _context.ChecklistEndereco.Remove(checklistEndereco);
            await _context.SaveChangesAsync();

            return checklistEndereco;
        }

        private bool ChecklistEnderecoExists(int id)
        {
            return _context.ChecklistEndereco.Any(e => e.Id == id);
        }

        [Authorize(Roles = Perfis.Administrador)]
        [HttpGet("gerarrelatorio")]
        public IActionResult GerarRelatorioExcel([FromServices] ChecklistEnderecoManager manager, DateTime? dataInicial, DateTime? dataFinal)
        {
            using (var excel = manager.GerarRelatorioExcel(dataInicial, dataFinal))
            {
                return File(excel.GetAsByteArray(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "RelatorioChecklistEndereco.xlsx");
            }
        }
    }
}
