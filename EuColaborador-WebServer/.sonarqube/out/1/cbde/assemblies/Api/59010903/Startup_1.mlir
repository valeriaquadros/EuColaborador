// Skipping function ConfigureServices(none), it contains poisonous unsupported syntaxes

// Skipping function Configure(none, none), it contains poisonous unsupported syntaxes

// Skipping function ConfigureIdentity(none), it contains poisonous unsupported syntaxes

// Skipping function ConfigureJWT(none), it contains poisonous unsupported syntaxes

func @_Api.Startup.AdicionarTransientes$Microsoft.Extensions.DependencyInjection.IServiceCollection$(none) -> () loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :116 :8) {
^entry (%_services : none):
%0 = cbde.alloca none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :116 :49)
cbde.store %_services, %0 : memref<none> loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :116 :49)
br ^0

^0: // SimpleBlock
%1 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :118 :12) // Not a variable of known type: services
%2 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :118 :12) // services.AddTransient<UsuarioManager>() (InvocationExpression)
%3 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :119 :12) // Not a variable of known type: services
%4 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :119 :35) // typeof(Startup) (TypeOfExpression)
%5 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :119 :12) // services.AddAutoMapper(typeof(Startup)) (InvocationExpression)
br ^1

^1: // ExitBlock
return

}
func @_Api.Startup.UpdateDatabase$Microsoft.AspNetCore.Builder.IApplicationBuilder$(none) -> () loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :122 :8) {
^entry (%_app : none):
%0 = cbde.alloca none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :122 :43)
cbde.store %_app, %0 : memref<none> loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :122 :43)
br ^0

^0: // SimpleBlock
%1 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :124 :37) // Not a variable of known type: app
%2 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :124 :37) // app.ApplicationServices (SimpleMemberAccessExpression)
%3 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :124 :37) // app.ApplicationServices                  .GetRequiredService<IServiceScopeFactory>() (InvocationExpression)
%4 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :124 :37) // app.ApplicationServices                  .GetRequiredService<IServiceScopeFactory>()                  .CreateScope() (InvocationExpression)
%6 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :127 :32) // Not a variable of known type: serviceScope
%7 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :127 :32) // serviceScope.ServiceProvider (SimpleMemberAccessExpression)
%8 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :127 :32) // serviceScope.ServiceProvider.GetService<CustomContext>() (InvocationExpression)
%10 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :128 :12) // Not a variable of known type: context
%11 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :128 :12) // context.Database (SimpleMemberAccessExpression)
%12 = cbde.unknown : none loc("C:\\Users\\hueli\\source\\repos\\EuColaborador-Backend\\Api\\Startup.cs" :128 :12) // context.Database.Migrate() (InvocationExpression)
br ^1

^1: // ExitBlock
return

}
