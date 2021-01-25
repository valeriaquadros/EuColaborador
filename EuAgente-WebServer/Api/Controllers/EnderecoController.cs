using Api.Application;
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
    public class EnderecoController : ControllerBase
    {
        private readonly CustomContext _context;

        public EnderecoController(CustomContext context)
        {
            _context = context;
        }

        // GET: api/Enderecoes
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Endereco>>> GetEndereco()
        {
            return await _context.Endereco.ToListAsync();
        }

        // GET: api/Enderecoes/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Endereco>> GetEndereco(int id)
        {
            var endereco = await _context.Endereco.FirstOrDefaultAsync(e => e.Id == id);

            if (endereco == null)
            {
                return NotFound();
            }

            return endereco;
        }

        // PUT: api/Enderecoes/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutEndereco(int id, Endereco endereco)
        {
            if (id != endereco.Id)
            {
                return BadRequest();
            }

            _context.Entry(endereco).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!EnderecoExists(id))
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

        // POST: api/Enderecoes
        [HttpPost]
        public async Task<ActionResult<Endereco>> PostEndereco([FromServices] EnderecoManager enderecoManager, Endereco endereco)
        {
            Endereco end = _context.Endereco.FirstOrDefault(x =>
                                                    x.Logradouro == endereco.Logradouro &&
                                                    x.Numero == endereco.Numero &&
                                                    x.CEP == endereco.CEP);
            if (end != null)
                return Ok(end);
            else
            {
                endereco = await enderecoManager.PreencherLatitudeLongitude(endereco);

                _context.Endereco.Add(endereco);
                await _context.SaveChangesAsync();
                return CreatedAtAction("GetEndereco", new { id = endereco.Id }, endereco);
            }
        }



        // DELETE: api/Enderecoes/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<Endereco>> DeleteEndereco(int id)
        {
            var endereco = await _context.Endereco.FindAsync(id);
            if (endereco == null)
            {
                return NotFound();
            }

            _context.Endereco.Remove(endereco);
            await _context.SaveChangesAsync();

            return endereco;
        }

        private bool EnderecoExists(int id)
        {
            return _context.Endereco.Any(e => e.Id == id);
        }
    }
}
