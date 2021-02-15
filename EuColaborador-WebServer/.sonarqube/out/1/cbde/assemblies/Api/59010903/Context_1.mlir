func @_Api.Contexts.CustomContext.OnConfiguring$Microsoft.EntityFrameworkCore.DbContextOptionsBuilder$(none) -> () loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :26 :8) {
^entry (%_optionsBuilder : none):
%0 = cbde.alloca none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :26 :46)
cbde.store %_optionsBuilder, %0 : memref<none> loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :26 :46)
br ^0

^0: // SimpleBlock
%1 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :28 :12) // Not a variable of known type: optionsBuilder
%2 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :28 :12) // optionsBuilder.UseLazyLoadingProxies() (InvocationExpression)
br ^1

^1: // ExitBlock
return

}
func @_Api.Contexts.CustomContext.OnModelCreating$Microsoft.EntityFrameworkCore.ModelBuilder$(none) -> () loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :31 :8) {
^entry (%_builder : none):
%0 = cbde.alloca none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :31 :48)
cbde.store %_builder, %0 : memref<none> loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :31 :48)
br ^0

^0: // SimpleBlock
%1 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :33 :12) // base (BaseExpression)
%2 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :33 :33) // Not a variable of known type: builder
%3 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :33 :12) // base.OnModelCreating(builder) (InvocationExpression)
// Skipped because MethodDeclarationSyntax or ClassDeclarationSyntax or NamespaceDeclarationSyntax: ConfigureBairro
%4 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :35 :28) // Not a variable of known type: builder
%5 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :35 :12) // ConfigureBairro(builder) (InvocationExpression)
// Skipped because MethodDeclarationSyntax or ClassDeclarationSyntax or NamespaceDeclarationSyntax: ConfigureCidade
%6 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :36 :28) // Not a variable of known type: builder
%7 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :36 :12) // ConfigureCidade(builder) (InvocationExpression)
// Skipped because MethodDeclarationSyntax or ClassDeclarationSyntax or NamespaceDeclarationSyntax: ConfigureRegiao
%8 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :37 :28) // Not a variable of known type: builder
%9 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :37 :12) // ConfigureRegiao(builder) (InvocationExpression)
// Skipped because MethodDeclarationSyntax or ClassDeclarationSyntax or NamespaceDeclarationSyntax: ProductionSeeds
%10 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :39 :28) // Not a variable of known type: builder
%11 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :39 :12) // ProductionSeeds(builder) (InvocationExpression)
br ^1

^1: // ExitBlock
return

}
func @_Api.Contexts.CustomContext.ProductionSeeds$Microsoft.EntityFrameworkCore.ModelBuilder$(none) -> () loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :42 :8) {
^entry (%_builder : none):
%0 = cbde.alloca none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :42 :44)
cbde.store %_builder, %0 : memref<none> loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :42 :44)
br ^0

^0: // SimpleBlock
// Skipped because MethodDeclarationSyntax or ClassDeclarationSyntax or NamespaceDeclarationSyntax: SeedEstado
%1 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :44 :28) // Not a variable of known type: builder
%2 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :44 :12) // SeedEstado.Seed(builder) (InvocationExpression)
// Skipped because MethodDeclarationSyntax or ClassDeclarationSyntax or NamespaceDeclarationSyntax: SeedCidade
%3 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :45 :28) // Not a variable of known type: builder
%4 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :45 :12) // SeedCidade.Seed(builder) (InvocationExpression)
// Skipped because MethodDeclarationSyntax or ClassDeclarationSyntax or NamespaceDeclarationSyntax: SeedRegiao
%5 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :46 :28) // Not a variable of known type: builder
%6 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :46 :12) // SeedRegiao.Seed(builder) (InvocationExpression)
// Skipped because MethodDeclarationSyntax or ClassDeclarationSyntax or NamespaceDeclarationSyntax: SeedBairro
%7 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :47 :28) // Not a variable of known type: builder
%8 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :47 :12) // SeedBairro.Seed(builder) (InvocationExpression)
// Skipped because MethodDeclarationSyntax or ClassDeclarationSyntax or NamespaceDeclarationSyntax: SeedEndemia
%9 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :48 :29) // Not a variable of known type: builder
%10 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :48 :12) // SeedEndemia.Seed(builder) (InvocationExpression)
// Skipped because MethodDeclarationSyntax or ClassDeclarationSyntax or NamespaceDeclarationSyntax: SeedTipoFoco
%11 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :49 :30) // Not a variable of known type: builder
%12 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Contexts\\Context.cs" :49 :12) // SeedTipoFoco.Seed(builder) (InvocationExpression)
br ^1

^1: // ExitBlock
return

}
// Skipping function ConfigureBairro(none), it contains poisonous unsupported syntaxes

// Skipping function ConfigureCidade(none), it contains poisonous unsupported syntaxes

// Skipping function ConfigureRegiao(none), it contains poisonous unsupported syntaxes

