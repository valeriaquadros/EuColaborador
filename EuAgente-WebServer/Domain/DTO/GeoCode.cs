﻿namespace Domain.DTO
{
    public class GeoCode
    {
        public string AuthenticationResultCode { get; set; }
        public string BrandLogoUri { get; set; }
        public string Copyright { get; set; }
        public Resourceset[] ResourceSets { get; set; }
        public int StatusCode { get; set; }
        public string StatusDescription { get; set; }
        public string TraceId { get; set; }
    }

    public class Resourceset
    {
        public int EstimatedTotal { get; set; }
        public Resource[] Resources { get; set; }
    }

    public class Resource
    {
        public string __type { get; set; }
        public float[] Bbox { get; set; }
        public string Name { get; set; }
        public Point Point { get; set; }
        public Address Address { get; set; }
        public string Confidence { get; set; }
        public string EntityType { get; set; }
        public Geocodepoint[] GeocodePoints { get; set; }
        public string[] MatchCodes { get; set; }
    }

    public class Point
    {
        public string Type { get; set; }
        public float[] Coordinates { get; set; }
    }

    public class Address
    {
        public string AddressLine { get; set; }
        public string AdminDistrict { get; set; }
        public string CountryRegion { get; set; }
        public string FormattedAddress { get; set; }
        public string Locality { get; set; }
        public string PostalCode { get; set; }
    }

    public class Geocodepoint
    {
        public string Type { get; set; }
        public float[] Coordinates { get; set; }
        public string CalculationMethod { get; set; }
        public string[] UsageTypes { get; set; }
    }

}
