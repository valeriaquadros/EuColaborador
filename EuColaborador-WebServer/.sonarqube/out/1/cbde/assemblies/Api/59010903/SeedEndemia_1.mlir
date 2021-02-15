func @_Api.Contexts.Seed.SeedEndemia.Seed$Microsoft.EntityFrameworkCore.ModelBuilder$(none) -> () loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Seed\\SeedEndemia.cs" :7 :8) {
^entry (%_modelBuilder : none):
%0 = cbde.alloca none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Seed\\SeedEndemia.cs" :7 :32)
cbde.store %_modelBuilder, %0 : memref<none> loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Seed\\SeedEndemia.cs" :7 :32)
br ^0

^0: // SimpleBlock
%1 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Seed\\SeedEndemia.cs" :9 :12) // Not a variable of known type: modelBuilder
%2 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Seed\\SeedEndemia.cs" :9 :12) // modelBuilder.Entity<Endemia>() (InvocationExpression)
%3 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Seed\\SeedEndemia.cs" :10 :16) // new Endemia { Id = 1, Nome = "Dengue" } (ObjectCreationExpression)
%4 = constant 1 : i32 loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Seed\\SeedEndemia.cs" :10 :35)
%5 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Seed\\SeedEndemia.cs" :10 :45) // "Dengue" (StringLiteralExpression)
%6 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Seed\\SeedEndemia.cs" :9 :12) // modelBuilder.Entity<Endemia>().HasData(                  new Endemia { Id = 1, Nome = "Dengue" }              ) (InvocationExpression)
br ^1

^1: // ExitBlock
return

}
