using Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace Data.Contexts.Seed
{
    public static class SeedTipoFoco
    {
        public static void Seed(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<TipoFoco>().HasData(
                new TipoFoco { Id = 1, EndemiaId = 1, Descricao = "Tonéis e depósitos de água" },
                new TipoFoco { Id = 2, EndemiaId = 1, Descricao = "Vasos sanitários" },
                new TipoFoco { Id = 3, EndemiaId = 1, Descricao = "Lixeiras dentro e fora de casa" },
                new TipoFoco { Id = 4, EndemiaId = 1, Descricao = "Tampinhas de garrafas, casca de ovo, latinhas, embalagens plásticas e de vidro, copos descartáveis ou qualquer outro objeto que possa acumular água" },
                new TipoFoco { Id = 5, EndemiaId = 1, Descricao = "Vasilhames para água de animais domésticos" },
                new TipoFoco { Id = 6, EndemiaId = 1, Descricao = "Bandejas externas de geladeiras." },
                new TipoFoco { Id = 7, EndemiaId = 1, Descricao = "Ralos de cozinha, de banheiro, de sauna ou ducha." },
                new TipoFoco { Id = 8, EndemiaId = 1, Descricao = "Pratinhos de vasos de plantas ou xaxins dentro e fora de casa." },
                new TipoFoco { Id = 9, EndemiaId = 1, Descricao = "Baldes e vasos de plantas vazias." },
                new TipoFoco { Id = 10, EndemiaId = 1, Descricao = "Bromélia ou outras plantas que possam acumular água." },
                new TipoFoco { Id = 11, EndemiaId = 1, Descricao = "Lagos, cascatas e espelhos de água decorativos." },
                new TipoFoco { Id = 12, EndemiaId = 1, Descricao = "Caixas de água, cisternas e poços" },
                new TipoFoco { Id = 13, EndemiaId = 1, Descricao = "Cavidade de árvores" },
                new TipoFoco { Id = 14, EndemiaId = 1, Descricao = "Piscinas" },
                new TipoFoco { Id = 15, EndemiaId = 1, Descricao = "Calhas e lajes" },
                new TipoFoco { Id = 16, EndemiaId = 1, Descricao = "Cacos de vidro nos muros" },
                new TipoFoco { Id = 17, EndemiaId = 1, Descricao = "Pneus velhos" },
                new TipoFoco { Id = 18, EndemiaId = 1, Descricao = "Garrafas pet e de vidro" },
                new TipoFoco { Id = 19, EndemiaId = 1, Descricao = "Entulho e lixo" },
                new TipoFoco { Id = 20, EndemiaId = 1, Descricao = "Canteiro de obras" }
            );
        }
    }
}