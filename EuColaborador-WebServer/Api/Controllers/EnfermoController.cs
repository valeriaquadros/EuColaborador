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
    public class EnfermoController : ControllerBase
    {
        private readonly CustomContext _context;

        public EnfermoController(CustomContext context)
        {
            _context = context;
        }

        // GET: api/Enfermo
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Enfermo>>> GetEnfermo()
        {
            return await _context.Enfermo.ToListAsync();
        }

        // GET: api/Enfermo/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Enfermo>> GetEnfermo(int id)
        {
            var enfermo = await _context.Enfermo.FindAsync(id);

            if (enfermo == null)
            {
                return NotFound();
            }

            return enfermo;
        }

        // PUT: api/Enfermo/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutEnfermo(int id, Enfermo enfermo)
        {
            if (id != enfermo.Id)
            {
                return BadRequest();
            }

            _context.Entry(enfermo).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!EnfermoExists(id))
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

        // POST: api/Enfermo
        [HttpPost]
        public async Task<ActionResult<Enfermo>> PostEnfermo([FromServices] EnfermoManager manager, Enfermo enfermo)
        {
            if (manager.CadastradoUltimaSemana(enfermo))
                return Ok();

            enfermo.EnfermoEndereco = new List<EnfermoEndereco>();
            foreach (var item in enfermo.Enderecos)
            {
                enfermo.EnfermoEndereco.Add(new EnfermoEndereco() { EnderecoId = item });
            }

            _context.Enfermo.Add(enfermo);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetEnfermo", new { id = enfermo.Id }, enfermo);
        }

        // DELETE: api/Enfermo/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<Enfermo>> DeleteEnfermo(int id)
        {
            var enfermo = await _context.Enfermo.FindAsync(id);
            if (enfermo == null)
            {
                return NotFound();
            }

            _context.Enfermo.Remove(enfermo);
            await _context.SaveChangesAsync();

            return enfermo;
        }

        private bool EnfermoExists(int id)
        {
            return _context.Enfermo.Any(e => e.Id == id);
        }

        [AllowAnonymous]
        [HttpGet("localizacoes")]
        public ActionResult<ConsultaMapa> BuscarLocalizacoes([FromServices] EnfermoManager manager, DateTime? dataInicial, DateTime? dataFinal)
        {
            ConsultaMapa consultaMapa = manager.BuscarConsultaMapa(dataInicial, dataFinal);

            return Ok(consultaMapa);
        }

        [Authorize(Roles = Perfis.Administrador)]
        [HttpGet("gerarrelatorio")]
        public IActionResult GerarRelatorioExcel([FromServices] EnfermoManager manager, DateTime? dataInicial, DateTime? dataFinal)
        {
            using (var excel = manager.GerarRelatorioExcel(dataInicial, dataFinal))
            {
                return File(excel.GetAsByteArray(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "RelatorioEnfermo.xlsx");
            }
        }
    }
}
