using CrossCutting.Constants;
using Domain.Entities;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using System;

namespace Data.Contexts.Seed
{
    public static class SeedUsuario
    {
        public static void Seed(ModelBuilder modelBuilder)
        {
            string ADMINISTRADOR_ID = "59AC88E9-3A2B-4721-8E45-C12E6A473684";
            modelBuilder.Entity<IdentityRole>().HasData(
                new IdentityRole { Id = ADMINISTRADOR_ID, Name = Perfis.Administrador, NormalizedName = Perfis.Administrador.ToUpper(), ConcurrencyStamp = "1D973AA2-7213-425C-8C3C-DA4329B990BF" }
            );

            modelBuilder.Entity<Pessoa>().HasData(
                new Pessoa
                {
                    Id = -1,
                    Nome = "Administrador",
                    Cpf = "12345678900",
                    DataNascimento = new DateTime(1990, 01, 01),
                    Sexo = 'M'
                }
            );

            string ADMIN_ID = "BE2D21DF-F681-4A69-A949-B066C3B71A9A";
            var hasher = new PasswordHasher<Usuario>();
            modelBuilder.Entity<Usuario>().HasData(
                new Usuario
                {
                    Id = ADMIN_ID,
                    UserName = "admin@eucolaborador.com",
                    NormalizedUserName = "ADMIN@EUCOLABORADOR.COM",
                    Email = "admin@eucolaborador.com",
                    NormalizedEmail = "ADMIN@EUCOLABORADOR.COM",
                    PhoneNumber = "(12) 3456-7890",
                    PasswordHash = hasher.HashPassword(null, "admin"),
                    EmailConfirmed = true,
                    SecurityStamp = "D36B85E2-18F1-443C-BD83-340919EC032C",
                    ConcurrencyStamp = "139877DC-66D3-4BFC-9D6B-B7EA6006B9E7",
                    LockoutEnabled = true,
                    PessoaId=-1
                }
            );

            modelBuilder.Entity<IdentityUserRole<string>>().HasData(new IdentityUserRole<string>
            {
                RoleId = ADMINISTRADOR_ID,
                UserId = ADMIN_ID
            });
        }
    }
}
