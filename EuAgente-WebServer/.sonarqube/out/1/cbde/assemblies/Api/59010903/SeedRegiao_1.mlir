func @_Api.Contexts.Seed.SeedRegiao.Seed$Microsoft.EntityFrameworkCore.ModelBuilder$(none) -> () loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :7 :8) {
^entry (%_modelBuilder : none):
%0 = cbde.alloca none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :7 :32)
cbde.store %_modelBuilder, %0 : memref<none> loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :7 :32)
br ^0

^0: // SimpleBlock
%1 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :9 :12) // Not a variable of known type: modelBuilder
%2 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :9 :12) // modelBuilder.Entity<Regiao>() (InvocationExpression)
%3 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :10 :16) // new Regiao { Id = 1, Nome = "A" } (ObjectCreationExpression)
%4 = constant 1 : i32 loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :10 :34)
%5 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :10 :44) // "A" (StringLiteralExpression)
%6 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :11 :16) // new Regiao { Id = 2, Nome = "B" } (ObjectCreationExpression)
%7 = constant 2 : i32 loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :11 :34)
%8 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :11 :44) // "B" (StringLiteralExpression)
%9 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :12 :16) // new Regiao { Id = 3, Nome = "C" } (ObjectCreationExpression)
%10 = constant 3 : i32 loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :12 :34)
%11 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :12 :44) // "C" (StringLiteralExpression)
%12 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :13 :16) // new Regiao { Id = 4, Nome = "D" } (ObjectCreationExpression)
%13 = constant 4 : i32 loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :13 :34)
%14 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :13 :44) // "D" (StringLiteralExpression)
%15 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :14 :16) // new Regiao { Id = 5, Nome = "E" } (ObjectCreationExpression)
%16 = constant 5 : i32 loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :14 :34)
%17 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :14 :44) // "E" (StringLiteralExpression)
%18 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuAgente-Backend\\Api\\Contexts\\Seed\\SeedRegiao.cs" :9 :12) // modelBuilder.Entity<Regiao>().HasData(                  new Regiao { Id = 1, Nome = "A" },                  new Regiao { Id = 2, Nome = "B" },                  new Regiao { Id = 3, Nome = "C" },                  new Regiao { Id = 4, Nome = "D" },                  new Regiao { Id = 5, Nome = "E" }              ) (InvocationExpression)
br ^1

^1: // ExitBlock
return

}
