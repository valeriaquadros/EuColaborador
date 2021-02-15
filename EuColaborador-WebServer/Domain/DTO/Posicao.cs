using System;

namespace Domain.DTO
{
    public class Posicao
    {
        public string Latitude { get; set; }
        public string Longitude { get; set; }
        public DateTime Data { get; set; }

        public Posicao()
        {
        }

        public Posicao(string latitude, string longitude, DateTime data)
        {
            Latitude = latitude;
            Longitude = longitude;
            Data = data;
        }
    }
}
