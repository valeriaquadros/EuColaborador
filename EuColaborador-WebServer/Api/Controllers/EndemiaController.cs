using Data.Contexts;
using Domain.Entities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Api.Controllers
{
    [Route("api/[controller]")]
    [Authorize]
    [ApiController]
    public class EndemiaController : ControllerBase
    {
        private readonly CustomContext _context;

        public EndemiaController(CustomContext context)
        {
            _context = context;
        }

        // GET: api/Endemias
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Endemia>>> GetEndemia()
        {
            return await _context.Endemia.Include(e => e.TiposFoco).ToListAsync();
        }

        // GET: api/Endemias/5
        [HttpGet("{id}")]
        public ActionResult<Endemia> GetEndemia(int id)
        {
            var endemia = _context.Endemia.Include(e => e.TiposFoco).FirstOrDefault(x => x.Id == id);

            if (endemia == null)
            {
                return NotFound();
            }

            return endemia;
        }

        // PUT: api/Endemias/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutEndemia(int id, Endemia endemia)
        {
            if (id != endemia.Id)
            {
                return BadRequest();
            }

            _context.Entry(endemia).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!EndemiaExists(id))
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

        // POST: api/Endemias
        [HttpPost]
        public async Task<ActionResult<Endemia>> PostEndemia(Endemia endemia)
        {
            _context.Endemia.Add(endemia);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetEndemia", new { id = endemia.Id }, endemia);
        }

        // DELETE: api/Endemias/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<Endemia>> DeleteEndemia(int id)
        {
            var endemia = await _context.Endemia.FindAsync(id);
            if (endemia == null)
            {
                return NotFound();
            }

            _context.Endemia.Remove(endemia);
            await _context.SaveChangesAsync();

            return endemia;
        }

        private bool EndemiaExists(int id)
        {
            return _context.Endemia.Any(e => e.Id == id);
        }
    }
}
