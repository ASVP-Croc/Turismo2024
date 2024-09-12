package com.speriamochemelacavo.turismo2024.services;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NominatimService {

    public String getInfoFromQuery(String query) throws IOException {
    	String url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/search")
                .queryParam("q", query)
                .queryParam("addressdetails", 1)
                .queryParam("countrycodes", "it")
                .queryParam("format", "jsonv2")
                .toUriString();
    	System.out.println(url);
        return getJsonFromUrl(url);
    }
    
    public String getInfoFromParameter(String amenity, String street, String postcode) throws IOException {
    	String url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/search")
                .queryParam("amenity", amenity)
                .queryParam("street", street)
                .queryParam("country", "Italia")
                .queryParam("postcode", postcode)
                .queryParam("addressdetails", 1)
                .queryParam("format", "jsonv2")
                .queryParam("countrycodes", "it")
                .toUriString();
    	System.out.println(url);
        return getJsonFromUrl(url);
    }
    
    public String getReverseLocationInfo(double lat, double lon) throws IOException {
        String url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/reverse")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("format", "json")
                .queryParam("countrycodes", "it")
                .toUriString();
        return getJsonFromUrl(url);
    }
    
    private String getJsonFromUrl(String url) throws IOException {
    	CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        return EntityUtils.toString(response.getEntity());
    }
}
