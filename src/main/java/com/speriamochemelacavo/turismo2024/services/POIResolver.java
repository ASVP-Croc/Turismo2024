package com.speriamochemelacavo.turismo2024.services;

import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	private Address addressResolver(LinkedHashMap<String, String> linkedHashMap, PointOfInterest pointOfInterest) {
		Address address = new Address();
		LinkedHashMap<String, String> otherLinkedHashMap = ((LinkedHashMap<String, String>)mapper.convertValue(linkedHashMap, LinkedHashMap.class));
		address.setAmenity(otherLinkedHashMap.get("amenity"));
		address.setHouse_number(otherLinkedHashMap.get("house_number"));
		address.setRoad(otherLinkedHashMap.get("road"));
		address.setQuarter(otherLinkedHashMap.get("quarter"));
		pointOfInterest.setCity(otherLinkedHashMap.get("city"));
		pointOfInterest.setPostcode(parseInt(otherLinkedHashMap.get("postcode")));
		return address;
	}
}
