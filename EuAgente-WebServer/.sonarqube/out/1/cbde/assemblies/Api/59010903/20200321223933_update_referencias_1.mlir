func @_Api.Migrations.update_referencias.Up$Microsoft.EntityFrameworkCore.Migrations.MigrationBuilder$(none) -> () loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :7 :8) {
^entry (%_migrationBuilder : none):
%0 = cbde.alloca none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :7 :35)
cbde.store %_migrationBuilder, %0 : memref<none> loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :7 :35)
br ^0

^0: // SimpleBlock
%1 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :9 :12) // Not a variable of known type: migrationBuilder
%2 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :10 :22) // "PK_EnfermoEndereco" (StringLiteralExpression)
%3 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :11 :23) // "EnfermoEndereco" (StringLiteralExpression)
%4 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :9 :12) // migrationBuilder.DropPrimaryKey(                  name: "PK_EnfermoEndereco",                  table: "EnfermoEndereco") (InvocationExpression)
%5 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :13 :12) // Not a variable of known type: migrationBuilder
%6 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :14 :22) // "IX_EnfermoEndereco_EnderecoId" (StringLiteralExpression)
%7 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :15 :23) // "EnfermoEndereco" (StringLiteralExpression)
%8 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :13 :12) // migrationBuilder.DropIndex(                  name: "IX_EnfermoEndereco_EnderecoId",                  table: "EnfermoEndereco") (InvocationExpression)
%9 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :17 :12) // Not a variable of known type: migrationBuilder
%10 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :18 :22) // "Id" (StringLiteralExpression)
%11 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :19 :23) // "EnfermoEndereco" (StringLiteralExpression)
%12 = constant 0 : i1 loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :20 :26) // false
%13 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :21 :28) // typeof(int) (TypeOfExpression)
%14 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :22 :25) // "integer" (StringLiteralExpression)
%15 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :17 :12) // migrationBuilder.AlterColumn<int>(                  name: "Id",                  table: "EnfermoEndereco",                  nullable: false,                  oldClrType: typeof(int),                  oldType: "integer") (InvocationExpression)
%16 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :23 :31) // "Npgsql:ValueGenerationStrategy" (StringLiteralExpression)
// Entity from another assembly: NpgsqlValueGenerationStrategy
%17 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :23 :65) // NpgsqlValueGenerationStrategy.IdentityByDefaultColumn (SimpleMemberAccessExpression)
%18 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :17 :12) // migrationBuilder.AlterColumn<int>(                  name: "Id",                  table: "EnfermoEndereco",                  nullable: false,                  oldClrType: typeof(int),                  oldType: "integer")                  .OldAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn) (InvocationExpression)
%19 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :25 :12) // Not a variable of known type: migrationBuilder
%20 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :26 :22) // "PK_EnfermoEndereco" (StringLiteralExpression)
%21 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :27 :23) // "EnfermoEndereco" (StringLiteralExpression)
%22 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :28 :25) // new[] { "EnderecoId", "EnfermoId" } (ImplicitArrayCreationExpression)
%23 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :28 :33) // "EnderecoId" (StringLiteralExpression)
%24 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :28 :47) // "EnfermoId" (StringLiteralExpression)
%25 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :25 :12) // migrationBuilder.AddPrimaryKey(                  name: "PK_EnfermoEndereco",                  table: "EnfermoEndereco",                  columns: new[] { "EnderecoId", "EnfermoId" }) (InvocationExpression)
br ^1

^1: // ExitBlock
return

}
func @_Api.Migrations.update_referencias.Down$Microsoft.EntityFrameworkCore.Migrations.MigrationBuilder$(none) -> () loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :31 :8) {
^entry (%_migrationBuilder : none):
%0 = cbde.alloca none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :31 :37)
cbde.store %_migrationBuilder, %0 : memref<none> loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :31 :37)
br ^0

^0: // SimpleBlock
%1 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :33 :12) // Not a variable of known type: migrationBuilder
%2 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :34 :22) // "PK_EnfermoEndereco" (StringLiteralExpression)
%3 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :35 :23) // "EnfermoEndereco" (StringLiteralExpression)
%4 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :33 :12) // migrationBuilder.DropPrimaryKey(                  name: "PK_EnfermoEndereco",                  table: "EnfermoEndereco") (InvocationExpression)
%5 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :37 :12) // Not a variable of known type: migrationBuilder
%6 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :38 :22) // "Id" (StringLiteralExpression)
%7 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :39 :23) // "EnfermoEndereco" (StringLiteralExpression)
%8 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :40 :22) // "integer" (StringLiteralExpression)
%9 = constant 0 : i1 loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :41 :26) // false
%10 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :42 :28) // typeof(int) (TypeOfExpression)
%11 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :37 :12) // migrationBuilder.AlterColumn<int>(                  name: "Id",                  table: "EnfermoEndereco",                  type: "integer",                  nullable: false,                  oldClrType: typeof(int)) (InvocationExpression)
%12 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :43 :28) // "Npgsql:ValueGenerationStrategy" (StringLiteralExpression)
// Entity from another assembly: NpgsqlValueGenerationStrategy
%13 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :43 :62) // NpgsqlValueGenerationStrategy.IdentityByDefaultColumn (SimpleMemberAccessExpression)
%14 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :37 :12) // migrationBuilder.AlterColumn<int>(                  name: "Id",                  table: "EnfermoEndereco",                  type: "integer",                  nullable: false,                  oldClrType: typeof(int))                  .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn) (InvocationExpression)
%15 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :45 :12) // Not a variable of known type: migrationBuilder
%16 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :46 :22) // "PK_EnfermoEndereco" (StringLiteralExpression)
%17 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :47 :23) // "EnfermoEndereco" (StringLiteralExpression)
%18 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :48 :24) // "Id" (StringLiteralExpression)
%19 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :45 :12) // migrationBuilder.AddPrimaryKey(                  name: "PK_EnfermoEndereco",                  table: "EnfermoEndereco",                  column: "Id") (InvocationExpression)
%20 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :50 :12) // Not a variable of known type: migrationBuilder
%21 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :51 :22) // "IX_EnfermoEndereco_EnderecoId" (StringLiteralExpression)
%22 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :52 :23) // "EnfermoEndereco" (StringLiteralExpression)
%23 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :53 :24) // "EnderecoId" (StringLiteralExpression)
%24 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Migrations\\20200321223933_update_referencias.cs" :50 :12) // migrationBuilder.CreateIndex(                  name: "IX_EnfermoEndereco_EnderecoId",                  table: "EnfermoEndereco",                  column: "EnderecoId") (InvocationExpression)
br ^1

^1: // ExitBlock
return

}
