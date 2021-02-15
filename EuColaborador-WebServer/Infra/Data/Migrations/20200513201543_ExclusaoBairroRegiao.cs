using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

namespace Data.Migrations
{
    public partial class ExclusaoBairroRegiao : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Endereco_Bairro_BairroId",
                table: "Endereco");

            migrationBuilder.DropTable(
                name: "Bairro");

            migrationBuilder.DropTable(
                name: "Regiao");

            migrationBuilder.DropIndex(
                name: "IX_Endereco_BairroId",
                table: "Endereco");

            migrationBuilder.DropColumn(
                name: "BairroId",
                table: "Endereco");

            migrationBuilder.AddColumn<string>(
                name: "Bairro",
                table: "Endereco",
                nullable: true);

            migrationBuilder.AddColumn<int>(
                name: "CidadeId",
                table: "Endereco",
                nullable: false,
                defaultValue: 1);

            migrationBuilder.CreateIndex(
                name: "IX_Endereco_CidadeId",
                table: "Endereco",
                column: "CidadeId");

            migrationBuilder.AddForeignKey(
                name: "FK_Endereco_Cidade_CidadeId",
                table: "Endereco",
                column: "CidadeId",
                principalTable: "Cidade",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Endereco_Cidade_CidadeId",
                table: "Endereco");

            migrationBuilder.DropIndex(
                name: "IX_Endereco_CidadeId",
                table: "Endereco");

            migrationBuilder.DropColumn(
                name: "Bairro",
                table: "Endereco");

            migrationBuilder.DropColumn(
                name: "CidadeId",
                table: "Endereco");

            migrationBuilder.AddColumn<int>(
                name: "BairroId",
                table: "Endereco",
                type: "integer",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.CreateTable(
                name: "Regiao",
                columns: table => new
                {
                    Id = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Nome = table.Column<string>(type: "text", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Regiao", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Bairro",
                columns: table => new
                {
                    Id = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    CidadeId = table.Column<int>(type: "integer", nullable: false),
                    Nome = table.Column<string>(type: "text", nullable: false),
                    RegiaoId = table.Column<int>(type: "integer", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Bairro", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Bairro_Cidade_CidadeId",
                        column: x => x.CidadeId,
                        principalTable: "Cidade",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Bairro_Regiao_RegiaoId",
                        column: x => x.RegiaoId,
                        principalTable: "Regiao",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.InsertData(
                table: "Regiao",
                columns: new[] { "Id", "Nome" },
                values: new object[,]
                {
                    { 1, "A" },
                    { 2, "B" },
                    { 3, "C" },
                    { 4, "D" },
                    { 5, "E" }
                });

            migrationBuilder.InsertData(
                table: "Bairro",
                columns: new[] { "Id", "CidadeId", "Nome", "RegiaoId" },
                values: new object[,]
                {
                    { 1, 5123, "Amambaí", 1 },
                    { 162, 5123, "Portal Caiobá", 4 },
                    { 163, 5123, "Portal Caiobá II", 4 },
                    { 164, 5123, "Portal do Panamá", 4 },
                    { 165, 5123, "Portal Itayara", 4 },
                    { 166, 5123, "Recanto das Paineiras", 4 },
                    { 167, 5123, "Recanto dos Pássaros", 4 },
                    { 168, 5123, "Residencial Ana Maria do Couto", 4 },
                    { 169, 5123, "Residencial Atlântico Sul", 4 },
                    { 170, 5123, "Residencial Betaville", 4 },
                    { 171, 5123, "Residencial Búzios", 4 },
                    { 172, 5123, "Residencial Estrela Park", 4 },
                    { 173, 5123, "Residencial Flamboian", 4 },
                    { 174, 5123, "Residencial Flores", 4 },
                    { 161, 5123, "Pólo Empresarial", 4 },
                    { 175, 5123, "Residencial Mário Cóvas", 4 },
                    { 177, 5123, "Residencial Rancharia", 4 },
                    { 178, 5123, "Residencial Serra Azul", 4 },
                    { 179, 5123, "Residencial Vila Bela", 4 },
                    { 180, 5123, "Santa Fé", 4 },
                    { 181, 5123, "Santo Antônio", 4 },
                    { 182, 5123, "São Francisco", 4 },
                    { 183, 5123, "Taquaral Bosque", 4 },
                    { 184, 5123, "Tayanã Park", 4 },
                    { 185, 5123, "Tiradentes", 4 },
                    { 186, 5123, "Universitário", 4 },
                    { 187, 5123, "Vila Adelina", 4 },
                    { 188, 5123, "Vila Aimoré", 4 },
                    { 189, 5123, "Vila Alba", 4 },
                    { 176, 5123, "Residencial Oliveira", 4 },
                    { 190, 5123, "Vila Albuquerque", 4 },
                    { 160, 5123, "Parque Residencial União", 4 },
                    { 158, 5123, "Parque Residencial Maria Aparecida Pedrossian", 4 },
                    { 130, 5123, "Loteamento Alto da Boa Vista", 3 },
                    { 131, 5123, "Loteamento Cristo Redentor", 3 },
                    { 132, 5123, "Loteamento Marçal de Souza", 3 },
                    { 133, 5123, "Loteamento Nova Serrana", 3 },
                    { 134, 5123, "Loteamento Paulo VI", 3 },
                    { 135, 5123, "Loteamento Porto Bello", 3 },
                    { 136, 5123, "Loteamento Rancho Alegre II", 3 },
                    { 137, 5123, "Loteamento Sóter", 3 },
                    { 138, 5123, "Loteamento Vespasiano Martins", 3 },
                    { 139, 5123, "Monte Carlo", 3 },
                    { 140, 5123, "Monte Castelo", 3 },
                    { 141, 5123, "Morada do Sol", 3 },
                    { 142, 5123, "Morada Verde", 3 },
                    { 159, 5123, "Parque Residencial Rita Vieira", 4 },
                    { 143, 5123, "Nova Lima", 3 },
                    { 145, 5123, "Núcleo Habitacional Universitárias", 3 },
                    { 146, 5123, "Núcleo Industrial", 3 },
                    { 147, 5123, "Panorama", 3 },
                    { 148, 5123, "Parque Atlântico", 3 },
                    { 149, 5123, "Parque Dallas", 3 },
                    { 150, 5123, "Parque do Lageado", 3 },
                    { 151, 5123, "Parque dos Laranjais", 4 },
                    { 152, 5123, "Parque dos Novos Estados", 4 },
                    { 153, 5123, "Parque dos Poderes", 4 },
                    { 154, 5123, "Parque Fazenda Imbirussu", 4 },
                    { 155, 5123, "Parque Isabel Garden's", 4 },
                    { 156, 5123, "Parque Residencial Azaléia", 4 },
                    { 157, 5123, "Parque Residencial Iracy Coelho Netto", 4 },
                    { 144, 5123, "Núcleo Habitacional Buriti", 3 },
                    { 129, 5123, "Lar do Trabalhador", 3 },
                    { 191, 5123, "Vila Almeida", 4 },
                    { 193, 5123, "Vila Antônio Vendas", 4 },
                    { 226, 5123, "Vila Nasser", 5 },
                    { 227, 5123, "Vila Neusa", 5 },
                    { 228, 5123, "Vila Nossa Senhora das Graças", 5 },
                    { 229, 5123, "Vila Nova Campo Grande", 5 },
                    { 230, 5123, "Vila Nova Capital", 5 },
                    { 231, 5123, "Vila Nova Olinda", 5 },
                    { 232, 5123, "Vila Olinda", 5 },
                    { 233, 5123, "Vila Palmira", 5 },
                    { 234, 5123, "Vila Paulo VI", 5 },
                    { 235, 5123, "Vila Piratininga", 5 },
                    { 236, 5123, "Vila Planalto", 5 },
                    { 237, 5123, "Vila Popular", 5 },
                    { 238, 5123, "Vila Progresso", 5 },
                    { 225, 5123, "Vila Nascente", 5 },
                    { 239, 5123, "Vila Rica", 5 },
                    { 241, 5123, "Vila Rosa Pires", 5 },
                    { 242, 5123, "Vila Santa Dorothéia", 5 },
                    { 243, 5123, "Vila Santa Luzia", 5 },
                    { 244, 5123, "Vila Santo Amaro", 5 },
                    { 245, 5123, "Vila Santo Eugênio", 5 },
                    { 246, 5123, "Vila São Jorge da Lagoa", 5 },
                    { 247, 5123, "Vila Serradinho", 5 },
                    { 248, 5123, "Vila Sílvia Regina", 5 },
                    { 249, 5123, "Vila Sobrinho", 5 },
                    { 250, 5123, "Vila Taquarussu", 5 },
                    { 251, 5123, "Vila Taveirópolis", 5 },
                    { 252, 5123, "Vila Vilas Boas", 5 },
                    { 253, 5123, "Vila Zoé", 5 },
                    { 240, 5123, "Vila Romana", 5 },
                    { 192, 5123, "Vila Anahy", 4 },
                    { 224, 5123, "Vila Morumbi", 5 },
                    { 222, 5123, "Vila Moreninha III", 5 },
                    { 194, 5123, "Vila Bandeirante", 4 },
                    { 195, 5123, "Vila Base Aérea", 4 },
                    { 196, 5123, "Vila Belo Horizonte", 4 },
                    { 197, 5123, "Vila Bordon", 4 },
                    { 198, 5123, "Vila Carlota", 4 },
                    { 199, 5123, "Vila Carvalho", 4 },
                    { 200, 5123, "Vila Cidade Morena", 4 },
                    { 201, 5123, "Vila Clélia", 5 },
                    { 202, 5123, "Vila Danúbio Azul", 5 },
                    { 203, 5123, "Vila do Polonês", 5 },
                    { 204, 5123, "Vila Duque de Caxias", 5 },
                    { 205, 5123, "Vila Eliane", 5 },
                    { 206, 5123, "Vila Entroncamento", 5 },
                    { 223, 5123, "Vila Moreninha IV", 5 },
                    { 207, 5123, "Vila Flório", 5 },
                    { 209, 5123, "Vila Glória", 5 },
                    { 210, 5123, "Vila Gomes", 5 },
                    { 211, 5123, "Vila Ieda", 5 },
                    { 212, 5123, "Vila Ipiranga", 5 },
                    { 213, 5123, "Vila Julieta", 5 },
                    { 214, 5123, "Vila Manoel da Costa Lima", 5 },
                    { 215, 5123, "Vila Manoel Sêcco Thomé", 5 },
                    { 216, 5123, "Vila Manoel Taveira", 5 },
                    { 217, 5123, "Vila Marcos Roberto", 5 },
                    { 218, 5123, "Vila Margarida", 5 },
                    { 219, 5123, "Vila Marli", 5 },
                    { 220, 5123, "Vila Matel", 5 },
                    { 221, 5123, "Vila Moreninha II", 5 },
                    { 208, 5123, "Vila Giocondo Orsi", 5 },
                    { 254, 5123, "Vivenda do Bosque", 5 },
                    { 128, 5123, "Lagoa Rica", 3 },
                    { 126, 5123, "Lagoa Dourada", 3 },
                    { 34, 5123, "Coophasul", 1 },
                    { 35, 5123, "Coophatrabalho", 1 },
                    { 36, 5123, "Coophavila II", 1 },
                    { 37, 5123, "Coronel Antonino", 1 },
                    { 38, 5123, "Cruzeiro", 1 },
                    { 39, 5123, "Guanandi", 1 },
                    { 40, 5123, "Guanandi II", 1 },
                    { 41, 5123, "Indaiá Park", 1 },
                    { 42, 5123, "Itanhangá Park", 1 },
                    { 43, 5123, "Jardim Aero Rancho", 1 },
                    { 44, 5123, "Jardim Aeroporto", 1 },
                    { 45, 5123, "Jardim Alto São Francisco", 1 },
                    { 46, 5123, "Jardim América", 1 },
                    { 33, 5123, "Coopharádio", 1 },
                    { 47, 5123, "Jardim Anache", 1 },
                    { 49, 5123, "Jardim Auxiliadora", 1 },
                    { 50, 5123, "Jardim Bálsamo", 1 },
                    { 51, 5123, "Jardim Batistão", 2 },
                    { 52, 5123, "Jardim Bela Vista", 2 },
                    { 53, 5123, "Jardim Bonança", 2 },
                    { 54, 5123, "Jardim Botafogo", 2 },
                    { 55, 5123, "Jardim Botânico", 2 },
                    { 56, 5123, "Jardim Botânico II", 2 },
                    { 57, 5123, "Jardim Campo Alto", 2 },
                    { 58, 5123, "Jardim Campo Belo", 2 },
                    { 59, 5123, "Jardim Campo Nobre", 2 },
                    { 60, 5123, "Jardim Campo Verde", 2 },
                    { 61, 5123, "Jardim Canguru", 2 },
                    { 48, 5123, "Jardim Autonomista", 1 },
                    { 62, 5123, "Jardim Carioca", 2 },
                    { 32, 5123, "Coophamat", 1 },
                    { 30, 5123, "Conjunto União II", 1 },
                    { 2, 5123, "Bosque de Avilan", 1 },
                    { 3, 5123, "Bosque Santa Mônica", 1 },
                    { 4, 5123, "Bosque Santa Mônica II", 1 },
                    { 5, 5123, "Cabreúva", 1 },
                    { 6, 5123, "Caiçara", 1 },
                    { 7, 5123, "Carandá Bosque", 1 },
                    { 8, 5123, "Center Park", 1 },
                    { 9, 5123, "Centro", 1 },
                    { 10, 5123, "Chácara Cachoeira", 1 },
                    { 11, 5123, "Chácara das Mansões", 1 },
                    { 12, 5123, "Chácara dos Poderes", 1 },
                    { 13, 5123, "Chácara José Antônio", 1 },
                    { 14, 5123, "Cidade Jardim", 1 },
                    { 31, 5123, "Coophafé", 1 },
                    { 15, 5123, "Cohafama", 1 },
                    { 17, 5123, "Conjunto Habitacional Estrela D'Alva I", 1 },
                    { 18, 5123, "Conjunto Habitacional Estrela D'Alva II", 1 },
                    { 19, 5123, "Conjunto Habitacional Estrela D'Alva III", 1 },
                    { 20, 5123, "Conjunto Habitacional Jardim Talismã", 1 },
                    { 21, 5123, "Conjunto Habitacional Nascente Segredo", 1 },
                    { 22, 5123, "Conjunto Iracy Coelho III", 1 },
                    { 23, 5123, "Conjunto José Abrão", 1 },
                    { 24, 5123, "Conjunto Parati II", 1 },
                    { 25, 5123, "Conjunto Residencial Botafogo", 1 },
                    { 26, 5123, "Conjunto Residencial Estrela do Sul", 1 },
                    { 27, 5123, "Conjunto Residencial Mata do Jacinto", 1 },
                    { 28, 5123, "Conjunto Residencial Octavio Pécora", 1 },
                    { 29, 5123, "Conjunto Residencial Recanto dos Rouxinóis", 1 },
                    { 16, 5123, "Conjunto Aero Rancho", 1 },
                    { 127, 5123, "Lagoa Park", 3 },
                    { 63, 5123, "Jardim Centenário", 2 },
                    { 65, 5123, "Jardim Colibrí", 2 },
                    { 98, 5123, "Jardim Panamá", 2 },
                    { 99, 5123, "Jardim Paradiso", 2 },
                    { 100, 5123, "Jardim Parati", 2 },
                    { 101, 5123, "Jardim Paulista", 3 },
                    { 102, 5123, "Jardim Pênfico", 3 },
                    { 103, 5123, "Jardim Petrópolis", 3 },
                    { 104, 5123, "Jardim Polonês", 3 },
                    { 105, 5123, "Jardim Presidente", 3 },
                    { 106, 5123, "Jardim Samambaia", 3 },
                    { 107, 5123, "Jardim Santa Emília", 3 },
                    { 108, 5123, "Jardim Santa Felicidade", 3 },
                    { 109, 5123, "Jardim São Bento", 3 },
                    { 110, 5123, "Jardim São Conrado", 3 },
                    { 97, 5123, "Jardim Ouro Preto", 2 },
                    { 111, 5123, "Jardim São Lourenço", 3 },
                    { 113, 5123, "Jardim Sayonara", 3 },
                    { 114, 5123, "Jardim Seminário", 3 },
                    { 115, 5123, "Jardim Sol Poente", 3 },
                    { 116, 5123, "Jardim Sumatra", 3 },
                    { 117, 5123, "Jardim Tarumã", 3 },
                    { 118, 5123, "Jardim Tijuca", 3 },
                    { 119, 5123, "Jardim Tijuca II", 3 },
                    { 120, 5123, "Jardim TV Morena", 3 },
                    { 121, 5123, "Jardim Uirapurú", 3 },
                    { 122, 5123, "Jardim Veraneio", 3 },
                    { 123, 5123, "Jardim Vida Nova", 3 },
                    { 124, 5123, "Jardim Zé Pereira", 3 },
                    { 125, 5123, "Lagoa da Cruz", 3 },
                    { 112, 5123, "Jardim Sarandí", 3 },
                    { 64, 5123, "Jardim Centro Oeste", 2 },
                    { 96, 5123, "Jardim Nossa Senhora do Perpétuo Socorro", 2 },
                    { 94, 5123, "Jardim Nhanhá", 2 },
                    { 66, 5123, "Jardim Colorado", 2 },
                    { 67, 5123, "Jardim Colúmbia", 2 },
                    { 68, 5123, "Jardim Corcovado", 2 },
                    { 69, 5123, "Jardim das Cerejeiras", 2 },
                    { 70, 5123, "Jardim das Hortências", 2 },
                    { 71, 5123, "Jardim das Macaúbas", 2 },
                    { 72, 5123, "Jardim das Reginas", 2 },
                    { 73, 5123, "Jardim dos Estados", 2 },
                    { 74, 5123, "Jardim Enseada dos Pássaros", 2 },
                    { 75, 5123, "Jardim Imá", 2 },
                    { 76, 5123, "Jardim Inápolis", 2 },
                    { 77, 5123, "Jardim Indianápolis", 2 },
                    { 78, 5123, "Jardim Itália", 2 },
                    { 95, 5123, "Jardim Noroeste", 2 },
                    { 79, 5123, "Jardim Itamaracá", 2 },
                    { 81, 5123, "Jardim Jacy", 2 },
                    { 82, 5123, "Jardim Jóquei Club", 2 },
                    { 83, 5123, "Jardim Leblon", 2 },
                    { 84, 5123, "Jardim Los Angeles", 2 },
                    { 85, 5123, "Jardim Manaíra", 2 },
                    { 86, 5123, "Jardim Mansur", 2 },
                    { 87, 5123, "Jardim Moema", 2 },
                    { 88, 5123, "Jardim Monte Alegre", 2 },
                    { 89, 5123, "Jardim Monte Líbano", 2 },
                    { 90, 5123, "Jardim Montevidéu", 2 },
                    { 91, 5123, "Jardim Monumento", 2 },
                    { 92, 5123, "Jardim Morenão", 2 },
                    { 93, 5123, "Jardim Nashiville", 2 },
                    { 80, 5123, "Jardim Itatiaia", 2 },
                    { 255, 5123, "Zona Rural", 5 }
                });

            migrationBuilder.CreateIndex(
                name: "IX_Endereco_BairroId",
                table: "Endereco",
                column: "BairroId");

            migrationBuilder.CreateIndex(
                name: "IX_Bairro_CidadeId",
                table: "Bairro",
                column: "CidadeId");

            migrationBuilder.CreateIndex(
                name: "IX_Bairro_RegiaoId",
                table: "Bairro",
                column: "RegiaoId");

            migrationBuilder.AddForeignKey(
                name: "FK_Endereco_Bairro_BairroId",
                table: "Endereco",
                column: "BairroId",
                principalTable: "Bairro",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
