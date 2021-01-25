using CrossCutting.Configurations;
using Domain.DTO;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using System;
using Web.Application;
using Web.Application.Util;

namespace Web
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllersWithViews().AddNToastNotifyToastr();
            ConfigureCookieAndSession(services);
            StartupConfig.ConfigurarJWT(Configuration, services);
            AdicionarServicos(services);

            services.AddResponseCompression();
        }

        private static void AdicionarServicos(IServiceCollection services)
        {
            services.AddTransient<GerenciarPerfilUsuarioApp>();
            services.AddTransient<MapaApp>();
            services.AddTransient<RelatorioApp>();
            services.AddTransient<UsuarioApp>();
        }

        public void ConfigureCookieAndSession(IServiceCollection services)
        {
            services.Configure<CookiePolicyOptions>(options =>
            {
                options.CheckConsentNeeded = context => false;
                options.MinimumSameSitePolicy = SameSiteMode.Strict;
            });

            services.Configure<CookieTempDataProviderOptions>(options =>
            {
                options.Cookie.IsEssential = true;
            });

            services.AddDistributedMemoryCache();

            services.AddSession(options =>
            {
                options.IdleTimeout = TimeSpan.FromMinutes(60);
                options.Cookie.Name = ".EuAgente.Session";
                options.Cookie.HttpOnly = true;
                options.Cookie.IsEssential = true;
                options.Cookie.SameSite = SameSiteMode.Strict;
            });
        }

        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            app.UseResponseCompression();

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseExceptionHandler("/Home/Error");
            }

            #pragma warning disable CS1998 // Async method lacks 'await' operators and will run synchronously
            app.UseStatusCodePages(async context =>
            {
                if (context.HttpContext.Response.StatusCode == 401 || context.HttpContext.Response.StatusCode == 403)
                {
                    context.HttpContext.Response.Redirect("/AcessoNegado");
                }
            });
            #pragma warning restore CS1998 // Async method lacks 'await' operators and will run synchronously

            app.UseStaticFiles();
            app.UseCookiePolicy();

            //Add User session
            app.UseSession();

            //Add JWToken to all incoming HTTP Request Header
            app.Use(async (context, next) =>
            {
                var usuario = context.Session.Get<LoginUsarioOutputModel>("Usuario");
                if (usuario!=null && !string.IsNullOrEmpty(usuario.Token))
                {
                    context.Request.Headers.Add("Authorization", "Bearer " + usuario.Token);
                }
                await next();
            });
            //Add JWToken Authentication service
            app.UseAuthentication();

            app.UseRouting();

            app.UseAuthorization();

            app.UseNToastNotify();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllerRoute(
                    name: "default",
                    pattern: "{controller=Home}/{action=Index}/{id?}");
            });
        }
    }
}
