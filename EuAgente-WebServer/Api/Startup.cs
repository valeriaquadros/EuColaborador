using Api.Application;
using AutoMapper;
using CrossCutting.Configurations;
using CrossCutting.Services;
using Data.Contexts;
using Domain.Entities;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.OpenApi.Models;
using System;

namespace Api
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        #region Configure Services
        #region Ambientes
        public void ConfigureDevelopmentServices(IServiceCollection services)
        {
            services.AddDbContext<CustomContext>(options
                => options.UseNpgsql(Configuration.GetConnectionString("DefaultConnection")));
            ConfigureServices(services);
        }

        public void ConfigureProductionServices(IServiceCollection services)
        {
            services.AddDbContext<CustomContext>(options
                => options.UseNpgsql(Environment.GetEnvironmentVariable("BDCONNECTIONSTRING")));
            ConfigureServices(services);
        }

        public void ConfigureServices(IServiceCollection services)
        {
            ConfigurarEmail(services);
            ConfigurarSwagger(services);
            services.AddCors();
            ConfigurarIdentity(services);
            StartupConfig.ConfigurarJWT(Configuration, services);
            AdicionarServicos(services);

            services.AddControllers();

            services.AddResponseCompression();
        }
        #endregion

        #region Individual Configuration
        private void ConfigurarSwagger(IServiceCollection services)
        {
            services.AddSwaggerGen(x =>
            {
                x.SwaggerDoc("v1", new OpenApiInfo { Title = this.GetType().Namespace, Version = "v1" });
                x.AddSecurityDefinition("Bearer", new OpenApiSecurityScheme
                {
                    Name = "Authorization",
                    Type = SecuritySchemeType.ApiKey,
                    Scheme = "Bearer",
                    BearerFormat = "JWT",
                    In = ParameterLocation.Header,
                    Description = "JWT Authorization header using the Bearer scheme."
                });
                x.AddSecurityRequirement(new OpenApiSecurityRequirement
                {
                    {
                          new OpenApiSecurityScheme
                            {
                                Reference = new OpenApiReference
                                {
                                    Type = ReferenceType.SecurityScheme,
                                    Id = "Bearer"
                                }
                            },
                            new string[] {}
                    }
                });
            });
        }

        private void ConfigurarEmail(IServiceCollection services)
        {
            string mailServer = Environment.GetEnvironmentVariable("MailServer");
            string mailPort = Environment.GetEnvironmentVariable("MailPort");
            string senderName = Environment.GetEnvironmentVariable("MailSenderName");
            string sender = Environment.GetEnvironmentVariable("MailSender");
            string password = Environment.GetEnvironmentVariable("MailPassword");
            if (!(string.IsNullOrWhiteSpace(mailServer)
                || string.IsNullOrWhiteSpace(mailPort)
                || string.IsNullOrWhiteSpace(senderName)
                || string.IsNullOrWhiteSpace(sender)
                || string.IsNullOrWhiteSpace(password)))
            {
                services.AddSingleton<IEmailSender>(new EmailSender(
                    new EmailSettings(mailServer, int.Parse(mailPort), senderName, sender, password)));
            }
            else
            {
                services.AddOptions();
                services.Configure<EmailSettings>(Configuration.GetSection("EmailSettings"));
                services.AddSingleton<IEmailSender, EmailSender>();
            }

        }

        private static void ConfigurarIdentity(IServiceCollection services)
        {
            services.AddIdentity<Usuario, IdentityRole>(config =>
            {
                config.User.RequireUniqueEmail = false;
                config.Password.RequiredLength = 6;
                config.Password.RequireDigit = false;
                config.Password.RequireLowercase = false;
                config.Password.RequireNonAlphanumeric = false;
                config.Password.RequireUppercase = false;
            })
                .AddEntityFrameworkStores<CustomContext>()
                .AddDefaultTokenProviders();
        }

        private static void AdicionarServicos(IServiceCollection services)
        {
            services.AddTransient<ChecklistEnderecoManager>();
            services.AddTransient<DenunciaFocoManager>();
            services.AddTransient<EnderecoManager>();
            services.AddTransient<EnfermoManager>();
            services.AddTransient<UsuarioManager>();
            services.AddAutoMapper(typeof(Startup));
        }
        #endregion
        #endregion

        #region Configure
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            app.UseResponseCompression();

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseSwagger();
            app.UseSwaggerUI(c =>
            {
                c.SwaggerEndpoint("/swagger/v1/swagger.json", this.GetType().Namespace);
            });

            UpdateDatabase(app);

            app.UseRouting();

            app.UseCors(x => x.AllowAnyOrigin().AllowAnyMethod().AllowAnyHeader());

            app.UseAuthentication();
            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }

        private static void UpdateDatabase(IApplicationBuilder app)
        {
            using var serviceScope = app.ApplicationServices
                .GetRequiredService<IServiceScopeFactory>()
                .CreateScope();
            using var context = serviceScope.ServiceProvider.GetService<CustomContext>();
            context.Database.Migrate();
        }
        #endregion
    }
}
