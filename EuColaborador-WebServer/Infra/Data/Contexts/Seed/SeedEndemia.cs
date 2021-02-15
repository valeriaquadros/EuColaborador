using Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace Data.Contexts.Seed
{
    public static class SeedEndemia
    {
        public static void Seed(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Endemia>().HasData(
                new Endemia { Id = 1, Nome = "Dengue" }
            );
        }
    }
}