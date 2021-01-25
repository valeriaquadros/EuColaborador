using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Data.Migrations
{
    public partial class InclusaoAdmin : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.InsertData(
                table: "AspNetRoles",
                columns: new[] { "Id", "ConcurrencyStamp", "Name", "NormalizedName" },
                values: new object[] { "59AC88E9-3A2B-4721-8E45-C12E6A473684", "1D973AA2-7213-425C-8C3C-DA4329B990BF", "Administrador", "ADMINISTRADOR" });

            migrationBuilder.InsertData(
                table: "Pessoa",
                columns: new[] { "Id", "Cpf", "DataNascimento", "Nome", "Sexo" },
                values: new object[] { -1, "12345678900", new DateTime(1990, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), "Administrador", 'M' });

            migrationBuilder.InsertData(
                table: "AspNetUsers",
                columns: new[] { "Id", "AccessFailedCount", "ConcurrencyStamp", "Email", "EmailConfirmed", "LockoutEnabled", "LockoutEnd", "NormalizedEmail", "NormalizedUserName", "PasswordHash", "PessoaId", "PhoneNumber", "PhoneNumberConfirmed", "SecurityStamp", "TwoFactorEnabled", "UserName" },
                values: new object[] { "BE2D21DF-F681-4A69-A949-B066C3B71A9A", 0, "139877DC-66D3-4BFC-9D6B-B7EA6006B9E7", "admin@euagente.com", true, true, null, "ADMIN@EUAGENTE.COM", "ADMIN@EUAGENTE.COM", "AQAAAAEAACcQAAAAEL8df60VhcM+26xc2henU/AMPfISmOqYnxuyG5YxTCZWatYP+BUv0KzPh5Dw584NLg==", -1, "(12) 3456-7890", false, "D36B85E2-18F1-443C-BD83-340919EC032C", false, "admin@euagente.com" });

            migrationBuilder.InsertData(
                table: "AspNetUserRoles",
                columns: new[] { "UserId", "RoleId" },
                values: new object[] { "BE2D21DF-F681-4A69-A949-B066C3B71A9A", "59AC88E9-3A2B-4721-8E45-C12E6A473684" });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "AspNetUserRoles",
                keyColumns: new[] { "UserId", "RoleId" },
                keyValues: new object[] { "BE2D21DF-F681-4A69-A949-B066C3B71A9A", "59AC88E9-3A2B-4721-8E45-C12E6A473684" });

            migrationBuilder.DeleteData(
                table: "AspNetRoles",
                keyColumn: "Id",
                keyValue: "59AC88E9-3A2B-4721-8E45-C12E6A473684");

            migrationBuilder.DeleteData(
                table: "AspNetUsers",
                keyColumn: "Id",
                keyValue: "BE2D21DF-F681-4A69-A949-B066C3B71A9A");

            migrationBuilder.DeleteData(
                table: "Pessoa",
                keyColumn: "Id",
                keyValue: -1);
        }
    }
}
