package com.speriamochemelacavo.turismo2024.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NominatimService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String getLocationInfoWithQuery(String query) {
        String url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/search")
                .queryParam("q", query)
                //.queryParam("limit", 1)
                .queryParam("addressdetails", 1)
                .queryParam("format", "json")
                .toUriString();
        return restTemplate.getForObject(url, String.class);
    }


    public String getLocationInfo(String amenity) {
        String url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/search")
                .queryParam("amenity", amenity)
                .queryParam("street", "Passetto")
                .queryParam("city", "Ancona")
                .queryParam("county", "Ancona")
                .queryParam("state", "Marche")
                .queryParam("country", "Italia")
                .queryParam("postcode", 60100)
                //.queryParam("limit", 1)
                .queryParam("addressdetails", 1)
                .queryParam("format", "json")
                .toUriString();
        return restTemplate.getForObject(url, String.class);
    }


    public String getReverseLocationInfo(double lat, double lon) {
        String url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/reverse")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("format", "json")
                .toUriString();
        return restTemplate.getForObject(url, String.class);
    }
}
