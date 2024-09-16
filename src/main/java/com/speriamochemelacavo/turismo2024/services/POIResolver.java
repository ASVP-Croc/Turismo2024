package com.speriamochemelacavo.turismo2024.services;

import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.metrics.StartupStep.Tags;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

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
		address.setHouse_number(checkKey(addressToConvert, "house_number"));
		address.setRoad(addressToConvert.get("road"));
		pointOfInterest.setCity(checkKey(addressToConvert, "city"));
		pointOfInterest.setPostcode(addressToConvert.get("postcode") != null ? addressToConvert.get("postcode") : "------");
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
				toSplit = "municipality, city, town, village";
			}
			default -> throw new IllegalArgumentException("Unexpected value: " + key);
		}
		
		String[] splitted = toSplit.split(", ");
		
		for (String toControll : splitted) {
			if (!toControll.isBlank()) toReturn = toReturn + " " + addressToCheck.getOrDefault(toControll, "");
		}
		
		if (toReturn.endsWith(",")) {
			toReturn = toReturn.substring(0, toReturn.length() - 1);
        }
		
		return toReturn.trim();
		
	}
}
