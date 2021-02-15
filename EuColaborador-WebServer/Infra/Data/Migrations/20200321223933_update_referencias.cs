using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

namespace Data.Migrations
{
    public partial class update_referencias : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropPrimaryKey(
                name: "PK_EnfermoEndereco",
                table: "EnfermoEndereco");

            migrationBuilder.DropIndex(
                name: "IX_EnfermoEndereco_EnderecoId",
                table: "EnfermoEndereco");

            migrationBuilder.AlterColumn<int>(
                name: "Id",
                table: "EnfermoEndereco",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "integer")
                .OldAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn);

            migrationBuilder.AddPrimaryKey(
                name: "PK_EnfermoEndereco",
                table: "EnfermoEndereco",
                columns: new[] { "EnderecoId", "EnfermoId" });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropPrimaryKey(
                name: "PK_EnfermoEndereco",
                table: "EnfermoEndereco");

            migrationBuilder.AlterColumn<int>(
                name: "Id",
                table: "EnfermoEndereco",
                type: "integer",
                nullable: false,
                oldClrType: typeof(int))
                .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn);

            migrationBuilder.AddPrimaryKey(
                name: "PK_EnfermoEndereco",
                table: "EnfermoEndereco",
                column: "Id");

            migrationBuilder.CreateIndex(
                name: "IX_EnfermoEndereco_EnderecoId",
                table: "EnfermoEndereco",
                column: "EnderecoId");
        }
    }
}
