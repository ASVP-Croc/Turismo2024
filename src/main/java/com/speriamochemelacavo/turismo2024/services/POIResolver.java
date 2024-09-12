package com.speriamochemelacavo.turismo2024.services;

import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

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
    	pointOfInterest.setAddress(addressResolver(addressToResolve, pointOfInterest));
        return pointOfInterest;
    }
/**
    private String addressResolver(LinkedHashMap<String, String> linkedHashMap) {
    	List<String> addressToForm = new ArrayList<String>();
    	addressToForm.add(linkedHashMap.get("road"));
    	if (linkedHashMap.containsKey("house_number"))
    		addressToForm.add(linkedHashMap.get("house_number"));
    	else
    		addressToForm.add("snc");
        String address = String.join(", ", addressToForm);
        return address;
    }
*/
	private Address addressResolver(LinkedHashMap<String, String> addressToResolve, PointOfInterest pointOfInterest) {
		Address address = new Address();
		LinkedHashMap<String, String> addressToConvert = ((LinkedHashMap<String, String>)mapper.convertValue(addressToResolve, LinkedHashMap.class));
		address.setAmenity(checkKey(addressToResolve, "amenity"));
		address.setHouse_number(checkKey(addressToResolve, "house_number"));
		address.setRoad(addressToConvert.get("road"));
		address.setQuarter(checkKey(addressToResolve, "quarter"));
		pointOfInterest.setCity(checkKey(addressToResolve, "city"));
		pointOfInterest.setPostcode(addressToConvert.get("postcode") != null ? addressToConvert.get("postcode") : "------");
		return address;
	}
	private String checkKey(LinkedHashMap<String, String> addressToCheck, String key) {
		String toReturn = " ";
		switch (key) {
		case "amenity" -> {
			String toSplit = "emergency, historic, military, natural, landuse, place, railway, man_made, aerialway, boundary, amenity, aeroway, club, craft, leisure, office, mountain_pass, shop, tourism, bridge, tunnel, waterway";
			String[] splitted = toSplit.split(", ");
			for (String toControll : splitted) {
				toReturn = toReturn + addressToCheck.getOrDefault(toControll, "");
			}
			
		}
		case "house_number" -> {
			String toSplit = "house_number, house_name";
			String[] splitted = toSplit.split(", ");
			for (String toControll : splitted) {
				toReturn = toReturn + addressToCheck.getOrDefault(toControll, "");
			}
			
		}
		case "city" -> {
			String toSplit = "municipality, city, town, village";
			String[] splitted = toSplit.split(", ");
			for (String toControll : splitted) {
				toReturn = toReturn + addressToCheck.getOrDefault(toControll, "");
			}
			
		}
		case "quarter" -> {
			String toSplit = "neighbourhood, allotments, quarter";
			String[] splitted = toSplit.split(", ");
			for (String toControll : splitted) {
				toReturn = toReturn + addressToCheck.getOrDefault(toControll, "");
			}
			
		}
		default ->
		throw new IllegalArgumentException("Unexpected value: " + key);
		}
		return toReturn;
		
	}
}
