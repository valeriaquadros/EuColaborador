func @_Api.Migrations.usuario.Up$Microsoft.EntityFrameworkCore.Migrations.MigrationBuilder$(none) -> () loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :6 :8) {
^entry (%_migrationBuilder : none):
%0 = cbde.alloca none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :6 :35)
cbde.store %_migrationBuilder, %0 : memref<none> loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :6 :35)
br ^0

^0: // SimpleBlock
%1 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :8 :12) // Not a variable of known type: migrationBuilder
%2 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :9 :22) // "PessoaId" (StringLiteralExpression)
%3 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :10 :23) // "AspNetUsers" (StringLiteralExpression)
%4 = constant 0 : i1 loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :11 :26) // false
%5 = constant 0 : i32 loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :12 :30)
%6 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :8 :12) // migrationBuilder.AddColumn<int>(                  name: "PessoaId",                  table: "AspNetUsers",                  nullable: false,                  defaultValue: 0) (InvocationExpression)
%7 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :14 :12) // Not a variable of known type: migrationBuilder
%8 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :15 :22) // "IX_AspNetUsers_PessoaId" (StringLiteralExpression)
%9 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :16 :23) // "AspNetUsers" (StringLiteralExpression)
%10 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :17 :24) // "PessoaId" (StringLiteralExpression)
%11 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :14 :12) // migrationBuilder.CreateIndex(                  name: "IX_AspNetUsers_PessoaId",                  table: "AspNetUsers",                  column: "PessoaId") (InvocationExpression)
%12 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :19 :12) // Not a variable of known type: migrationBuilder
%13 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :20 :22) // "FK_AspNetUsers_Pessoa_PessoaId" (StringLiteralExpression)
%14 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :21 :23) // "AspNetUsers" (StringLiteralExpression)
%15 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :22 :24) // "PessoaId" (StringLiteralExpression)
%16 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :23 :32) // "Pessoa" (StringLiteralExpression)
%17 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :24 :33) // "Id" (StringLiteralExpression)
// Entity from another assembly: ReferentialAction
%18 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :25 :26) // ReferentialAction.Cascade (SimpleMemberAccessExpression)
%19 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :19 :12) // migrationBuilder.AddForeignKey(                  name: "FK_AspNetUsers_Pessoa_PessoaId",                  table: "AspNetUsers",                  column: "PessoaId",                  principalTable: "Pessoa",                  principalColumn: "Id",                  onDelete: ReferentialAction.Cascade) (InvocationExpression)
br ^1

^1: // ExitBlock
return

}
func @_Api.Migrations.usuario.Down$Microsoft.EntityFrameworkCore.Migrations.MigrationBuilder$(none) -> () loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :28 :8) {
^entry (%_migrationBuilder : none):
%0 = cbde.alloca none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :28 :37)
cbde.store %_migrationBuilder, %0 : memref<none> loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :28 :37)
br ^0

^0: // SimpleBlock
%1 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :30 :12) // Not a variable of known type: migrationBuilder
%2 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :31 :22) // "FK_AspNetUsers_Pessoa_PessoaId" (StringLiteralExpression)
%3 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :32 :23) // "AspNetUsers" (StringLiteralExpression)
%4 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :30 :12) // migrationBuilder.DropForeignKey(                  name: "FK_AspNetUsers_Pessoa_PessoaId",                  table: "AspNetUsers") (InvocationExpression)
%5 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :34 :12) // Not a variable of known type: migrationBuilder
%6 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :35 :22) // "IX_AspNetUsers_PessoaId" (StringLiteralExpression)
%7 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :36 :23) // "AspNetUsers" (StringLiteralExpression)
%8 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :34 :12) // migrationBuilder.DropIndex(                  name: "IX_AspNetUsers_PessoaId",                  table: "AspNetUsers") (InvocationExpression)
%9 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :38 :12) // Not a variable of known type: migrationBuilder
%10 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :39 :22) // "PessoaId" (StringLiteralExpression)
%11 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :40 :23) // "AspNetUsers" (StringLiteralExpression)
%12 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Migrations\\20200318231358_usuario.cs" :38 :12) // migrationBuilder.DropColumn(                  name: "PessoaId",                  table: "AspNetUsers") (InvocationExpression)
br ^1

^1: // ExitBlock
return

}
