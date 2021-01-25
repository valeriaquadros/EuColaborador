using Data.Contexts.Seed;
using Domain.Entities;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace Data.Contexts
{
    public class CustomContext : IdentityDbContext<Usuario>
    {
        public DbSet<ChecklistEndereco> ChecklistEndereco { get; set; }
        public DbSet<DenunciaFoco> DenunciaFoco { get; set; }
        public DbSet<Endemia> Endemia { get; set; }
        public DbSet<Endereco> Endereco { get; set; }
        public DbSet<Enfermo> Enfermo { get; set; }
        public DbSet<Pessoa> Pessoa { get; set; }
        public DbSet<EnfermoEndereco> EnfermoEndereco { get; set; }
        public DbSet<TipoFoco> TipoFoco { get; set; }

        public CustomContext(DbContextOptions<CustomContext> options) : base(options)
        {
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseLazyLoadingProxies();
        }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);
            ProductionSeeds(builder);
            DevSeeds(builder);
        }

        private static void ProductionSeeds(ModelBuilder builder)
        {
            SeedEndemia.Seed(builder);
            SeedTipoFoco.Seed(builder);
            SeedUsuario.Seed(builder);
        }

        private static void DevSeeds(ModelBuilder builder)
        {
            SeedMapa.Seed(builder);
        }
    }
}