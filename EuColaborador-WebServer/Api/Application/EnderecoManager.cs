using Domain.DTO;
using Domain.Entities;
using Newtonsoft.Json;
using System;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace Api.Application
{
    public class EnderecoManager
    {
        public async Task<Endereco> PreencherLatitudeLongitude(Endereco endereco)
        {
            if (endereco.Logradouro != null && (endereco.Latitude == null || endereco.Longitude == null))
            {
                string url = GerarUrlBuscaLatitudeLongitude(endereco);

                GeoCode geoCode = await BuscarGeoCode(url);
                MapearLatitudadeLongitudade(endereco, geoCode);
            }

            return endereco;
        }

        #region PreencherLatitudeLongitude
        private string GerarUrlBuscaLatitudeLongitude(Endereco endereco)
        {
            StringBuilder sb = new StringBuilder("http://dev.virtualearth.net/REST/v1/Locations?maxResults=1&countryRegion=BR");
            sb.Append("&postalCode=").Append(endereco.CEP);
            sb.Append("&addressLine=").Append(endereco.Logradouro).Append(" ").Append(endereco.Numero);
            sb.Append("&key=").Append(Environment.GetEnvironmentVariable("BINGAPIKEY"));

            return sb.ToString();
        }

        private static async Task<GeoCode> BuscarGeoCode(string url)
        {
            HttpClient client = new HttpClient();
            HttpResponseMessage httpResponseMessage = await client.GetAsync(url);
            string contentResponse = await httpResponseMessage.Content.ReadAsStringAsync();
            GeoCode geoCode = JsonConvert.DeserializeObject<GeoCode>(contentResponse);
            return geoCode;
        }

        private static void MapearLatitudadeLongitudade(Endereco endereco, GeoCode geoCode)
        {
            endereco.Latitude = geoCode.ResourceSets.FirstOrDefault()?.Resources.FirstOrDefault()?.Point.Coordinates[0].ToString();
            endereco.Longitude = geoCode.ResourceSets.FirstOrDefault()?.Resources.FirstOrDefault()?.Point.Coordinates[1].ToString();
        }
        #endregion
    }
}
