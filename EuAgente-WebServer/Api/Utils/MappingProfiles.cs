using AutoMapper;
using Domain.DTO;
using Domain.Entities;

namespace Api.Utils
{
    public class MappingProfiles : Profile
    {
        public MappingProfiles()
        {
            CreateMap<Usuario, UsuarioOutputModel>()
                .ForMember(destination => destination.CPF, opts => opts.MapFrom(source => source.Pessoa.Cpf))
                .ForMember(destination => destination.Nome, opts => opts.MapFrom(source => source.Pessoa.Nome));
        }
    }
}