func @_Api.Migrations.adicao_usuario.Up$Microsoft.EntityFrameworkCore.Migrations.MigrationBuilder$(none) -> () loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :6 :8) {
^entry (%_migrationBuilder : none):
%0 = cbde.alloca none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :6 :35)
cbde.store %_migrationBuilder, %0 : memref<none> loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :6 :35)
br ^0

^0: // SimpleBlock
%1 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :8 :12) // Not a variable of known type: migrationBuilder
%2 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :9 :22) // "Sexo" (StringLiteralExpression)
%3 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :10 :23) // "Pessoa" (StringLiteralExpression)
%4 = constant 0 : i1 loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :11 :26) // false
%5 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :12 :28) // typeof(bool) (TypeOfExpression)
%6 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :13 :25) // "boolean" (StringLiteralExpression)
%7 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :8 :12) // migrationBuilder.AlterColumn<char>(                  name: "Sexo",                  table: "Pessoa",                  nullable: false,                  oldClrType: typeof(bool),                  oldType: "boolean") (InvocationExpression)
br ^1

^1: // ExitBlock
return

}
func @_Api.Migrations.adicao_usuario.Down$Microsoft.EntityFrameworkCore.Migrations.MigrationBuilder$(none) -> () loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :16 :8) {
^entry (%_migrationBuilder : none):
%0 = cbde.alloca none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :16 :37)
cbde.store %_migrationBuilder, %0 : memref<none> loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :16 :37)
br ^0

^0: // SimpleBlock
%1 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :18 :12) // Not a variable of known type: migrationBuilder
%2 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :19 :22) // "Sexo" (StringLiteralExpression)
%3 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :20 :23) // "Pessoa" (StringLiteralExpression)
%4 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :21 :22) // "boolean" (StringLiteralExpression)
%5 = constant 0 : i1 loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :22 :26) // false
%6 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :23 :28) // typeof(char) (TypeOfExpression)
%7 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318022633_adicao_usuario.cs" :18 :12) // migrationBuilder.AlterColumn<bool>(                  name: "Sexo",                  table: "Pessoa",                  type: "boolean",                  nullable: false,                  oldClrType: typeof(char)) (InvocationExpression)
br ^1

^1: // ExitBlock
return

}
