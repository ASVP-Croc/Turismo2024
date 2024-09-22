package com.speriamochemelacavo.turismo2024.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PoIType;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;

import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterestFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

import static java.lang.Float.parseFloat;

@Service
public class POIResolver extends ElementResolver<PointOfInterest>{

    @Override
    protected PointOfInterest elementResolver(LinkedHashMap<String, Object> POIToResolve){
    	PointOfInterest pointOfInterest = new PointOfInterest();
    	pointOfInterest.setName(POIToResolve.get("name").toString());
    	pointOfInterest.setDescription(POIToResolve.get("type").toString());
    	pointOfInterest.setLatitude(parseFloat(POIToResolve.get("lat").toString()));
    	pointOfInterest.setLongitude(parseFloat(POIToResolve.get("lon").toString()));
    	LinkedHashMap<String, String> addressToResolve = (LinkedHashMap<String, String>)mapper.convertValue(POIToResolve.get("address"), LinkedHashMap.class);
    	Address addressResolved = addressResolver(addressToResolve, pointOfInterest);
    	pointOfInterest.setAddress(addressResolved);
        return pointOfInterest;
    }

	private Address addressResolver(LinkedHashMap<String, String> addressToResolve, PointOfInterest pointOfInterest) {
		Address address = new Address();
		LinkedHashMap<String, String> addressToConvert = ((LinkedHashMap<String, String>)mapper.convertValue(addressToResolve, LinkedHashMap.class));
		address.setAmenity(checkKey(addressToConvert, "amenity"));
		address.setHouseNumber(Integer.parseInt(checkKey(addressToConvert, "house_number").isBlank() ? "0" : checkKey(addressToConvert, "house_number")));
		address.setRoad(addressToConvert.get("road"));
		pointOfInterest.setCity(checkKey(addressToConvert, "city"));
		pointOfInterest.setPostalcode(addressToConvert.get("postcode") != null ? addressToConvert.get("postcode") : "------");
		return address;
	}
	private String checkKey(LinkedHashMap<String, String> addressToCheck, String key) {
		String toReturn = "";
		String toSplit = "";
		
		switch (key) {
			case "amenity" -> {
				toSplit = "emergency, historic, military, natural, landuse, place, railway, man_made, aerialway, boundary, amenity, aeroway, club, craft, leisure, office, mountain_pass, shop, tourism, bridge, tunnel, waterway";
			}
			case "house_number" -> {
				toSplit = "house_number, house_name";
			}
			case "city" -> {
				toSplit = "village, town, city, municipality";
			}
			default -> throw new IllegalArgumentException("Unexpected value: " + key);
		}
		
		String[] splitted = toSplit.split(", ");
		
		for (String toControll : splitted) {
			toReturn = toReturn + addressToCheck.getOrDefault(toControll, "");
			if (!addressToCheck.getOrDefault(toControll, "").isBlank()) {
				toReturn = toReturn + ", ";
			}
		}
		
		while (toReturn.endsWith(", ")) {
			toReturn = toReturn.substring(0, toReturn.length() - 2);
        }
		
		return toReturn;
		
	}
}
