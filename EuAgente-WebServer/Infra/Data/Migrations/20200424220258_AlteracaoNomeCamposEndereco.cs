using Microsoft.EntityFrameworkCore.Migrations;

namespace Data.Migrations
{
    public partial class AlteracaoNomeCamposEndereco : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "CoordenadaX",
                table: "Endereco");

            migrationBuilder.DropColumn(
                name: "CoordenadaY",
                table: "Endereco");

            migrationBuilder.AddColumn<string>(
                name: "Latitude",
                table: "Endereco",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Longitude",
                table: "Endereco",
                nullable: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Latitude",
                table: "Endereco");

            migrationBuilder.DropColumn(
                name: "Longitude",
                table: "Endereco");

            migrationBuilder.AddColumn<string>(
                name: "CoordenadaX",
                table: "Endereco",
                type: "text",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "CoordenadaY",
                table: "Endereco",
                type: "text",
                nullable: true);
        }
    }
}
