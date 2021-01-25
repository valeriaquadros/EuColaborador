using Microsoft.EntityFrameworkCore.Migrations;

namespace Data.Migrations
{
    public partial class adicao_usuario : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<char>(
                name: "Sexo",
                table: "Pessoa",
                nullable: false,
                oldClrType: typeof(bool),
                oldType: "boolean");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<bool>(
                name: "Sexo",
                table: "Pessoa",
                type: "boolean",
                nullable: false,
                oldClrType: typeof(char));
        }
    }
}
