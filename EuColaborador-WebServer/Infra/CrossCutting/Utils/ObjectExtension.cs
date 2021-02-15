using Newtonsoft.Json;
using System.Net.Http;
using System.Text;

namespace CrossCutting.Utils
{
    public static class ObjectExtension
    {
        public static StringContent ToStringContent(this object value)
        {
            var json = JsonConvert.SerializeObject(value);
            return new StringContent(json, Encoding.UTF8, "application/json");
        }
    }
}
