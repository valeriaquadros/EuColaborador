using Api.Application;
using CrossCutting.Constants;
using Data.Contexts;
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
    public class DenunciaFocoController : ControllerBase
    {
        private readonly CustomContext _context;

        public DenunciaFocoController(CustomContext context)
        {
            _context = context;
        }

        // GET: api/DenunciaFoco
        [HttpGet]
        public async Task<ActionResult<IEnumerable<DenunciaFoco>>> GetDenunciaFoco()
        {
            return await _context.DenunciaFoco.ToListAsync();
        }

        // GET: api/DenunciaFoco/5
        [HttpGet("{id}")]
        public async Task<ActionResult<DenunciaFoco>> GetDenunciaFoco(int id)
        {
            var denunciaFoco = await _context.DenunciaFoco.FindAsync(id);

            if (denunciaFoco == null)
            {
                return NotFound();
            }

            return denunciaFoco;
        }

        // PUT: api/DenunciaFoco/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutDenunciaFoco(int id, DenunciaFoco denunciaFoco)
        {
            if (id != denunciaFoco.Id)
            {
                return BadRequest();
            }

            _context.Entry(denunciaFoco).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DenunciaFocoExists(id))
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

        // POST: api/DenunciaFoco
        [HttpPost]
        public async Task<ActionResult<DenunciaFoco>> PostDenunciaFoco(DenunciaFoco denunciaFoco)
        {
            _context.DenunciaFoco.Add(denunciaFoco);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetDenunciaFoco", new { id = denunciaFoco.Id }, denunciaFoco);
        }

        // DELETE: api/DenunciaFoco/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<DenunciaFoco>> DeleteDenunciaFoco(int id)
        {
            var denunciaFoco = await _context.DenunciaFoco.FindAsync(id);
            if (denunciaFoco == null)
            {
                return NotFound();
            }

            _context.DenunciaFoco.Remove(denunciaFoco);
            await _context.SaveChangesAsync();

            return denunciaFoco;
        }

        private bool DenunciaFocoExists(int id)
        {
            return _context.DenunciaFoco.Any(e => e.Id == id);
        }

        [Authorize(Roles = Perfis.Administrador)]
        [HttpGet("gerarrelatorio")]
        public IActionResult GerarRelatorioExcel([FromServices] DenunciaFocoManager manager, DateTime? dataInicial, DateTime? dataFinal)
        {
            using (var excel = manager.GerarRelatorioExcel(dataInicial, dataFinal))
            {
                return File(excel.GetAsByteArray(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "RelatorioDenunciaFoco.xlsx");
            }
        }
    }
}
