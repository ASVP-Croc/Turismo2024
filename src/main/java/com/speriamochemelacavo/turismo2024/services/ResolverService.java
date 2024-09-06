package com.speriamochemelacavo.turismo2024.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@Service
public class ResolverService {
    
    private ObjectMapper mapper = new ObjectMapper();

    public PointOfInterest getPOI(String poi) throws JsonProcessingException {
        LinkedHashMap<String, String> result = (LinkedHashMap<String, String>) mapper.readValue(poi, LinkedHashMap.class);
        return POIResolver(result);
    }

    public List<PointOfInterest> getPOIs(String POIs) throws JsonProcessingException {
        List<PointOfInterest> poisToReturn = new ArrayList<>();
        ArrayList<LinkedHashMap<String, String>> result = (ArrayList<LinkedHashMap<String, String>>) mapper.readValue(POIs, ArrayList.class);
        result.stream().forEach(l-> poisToReturn.add(POIResolver(l)));
        return poisToReturn;
    }

    private PointOfInterest POIResolver(LinkedHashMap<String, String> linkedHashMap) {
    	PointOfInterest pointOfInterest = new PointOfInterest();
    	pointOfInterest.setName(linkedHashMap.get("name"));
    	pointOfInterest.setLatitude(parseFloat(linkedHashMap.get("lat")));
    	pointOfInterest.setLongitude(parseFloat(linkedHashMap.get("lon")));
    	pointOfInterest.setAddress(linkedHashMap.get("address"));
        return pointOfInterest;
    }

//    private Address addressResolver(LinkedHashMap<String, String> linkedHashMap) {
//        Address address = new Address();
//        LinkedHashMap<String, String> otherLinkedHashMap = ((LinkedHashMap<String, String>)mapper.convertValue(linkedHashMap, LinkedHashMap.class));
//        address.setAmenity(otherLinkedHashMap.get("amenity"));
//        address.setHouse_number(otherLinkedHashMap.get("house_number"));
//        address.setRoad(otherLinkedHashMap.get("road"));
//        address.setQuarter(otherLinkedHashMap.get("quarter"));
//        address.setCity(otherLinkedHashMap.get("city"));
//        address.setCounty(otherLinkedHashMap.get("county"));
//        address.setState(otherLinkedHashMap.get("state"));
//        address.setPostcode(parseInt(otherLinkedHashMap.get("postcode")));
//        address.setCountry(otherLinkedHashMap.get("country"));
//        address.setCountry_code(otherLinkedHashMap.get("country_code"));
//        return address;
//    }
}
