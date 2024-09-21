package com.speriamochemelacavo.turismo2024.services;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
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
//    	TODO togliere prima della produzione
    	System.out.println(url);
        return getJsonFromUrl(url);
    }
    
    public String getInfoFromParameter(String amenity, String street, String postalcode) throws IOException {
    	UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/search");
    	if (amenity != "" && amenity != null) {
    		url.queryParam("amenity", amenity);
    	}
    	if (street != "" && street != null) {
    		url.queryParam("street", street);
    	}
    	if (postalcode != "" && postalcode != null) {
    		url.queryParam("postalcode", postalcode);
    	}
    	url.queryParam("country", "Italia")
        .queryParam("addressdetails", 1)
        .queryParam("format", "jsonv2")
        .queryParam("countrycodes", "it");
//    	TODO togliere prima della produzione
    	String toReturn = url.toUriString();
    	System.out.println(toReturn);
        return getJsonFromUrl(toReturn);
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
