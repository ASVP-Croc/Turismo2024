package com.speriamochemelacavo.turismo2024.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
public class POIResolverService implements ResolverService<PointOfInterest>{
    
    private ObjectMapper mapper = new ObjectMapper();

    public PointOfInterest getPOI(String poi) throws JsonProcessingException {
        LinkedHashMap<String, Object> result = mapper.readValue(poi, LinkedHashMap.class);
        return POIResolver(result);
    }

    public List<PointOfInterest> getPOIs(String POIs) throws JsonProcessingException {
        List<PointOfInterest> poisToReturn = new ArrayList<>();
        ArrayList<LinkedHashMap<String, Object>> result = mapper.readValue(POIs, ArrayList.class);
        result.stream().forEach(l-> {
			try {
				poisToReturn.add(POIResolver(l));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        return poisToReturn;
    }

    private PointOfInterest POIResolver(LinkedHashMap<String, Object> linkedHashMap) throws JsonMappingException, JsonProcessingException {
    	PointOfInterest pointOfInterest = new PointOfInterest();
    	pointOfInterest.setName(linkedHashMap.get("name").toString());
    	pointOfInterest.setLatitude(parseFloat(linkedHashMap.get("lat").toString()));
    	pointOfInterest.setLongitude(parseFloat(linkedHashMap.get("lon").toString()));
    	System.out.println(linkedHashMap.get("address").toString());
    	pointOfInterest.setAddress(addressResolver(mapper.readValue(linkedHashMap.get("address").toString(), LinkedHashMap.class)));
        return pointOfInterest;
    }

    private String addressResolver(LinkedHashMap<String, String> linkedHashMap) {
        String address = "";
        address.concat(linkedHashMap.get("amenity"));
        address.concat(linkedHashMap.get("house_number"));
        address.concat(linkedHashMap.get("road"));
        address.concat(linkedHashMap.get("quarter"));
        address.concat(linkedHashMap.get("city"));
        address.concat(linkedHashMap.get("county"));
        address.concat(linkedHashMap.get("state"));
        address.concat(linkedHashMap.get("postcode"));
        address.concat(linkedHashMap.get("country"));
        address.concat(linkedHashMap.get("country_code"));
        return address;
    }

	@Override
	public PointOfInterest getElement() {
		// TODO Auto-generated method stub
		return null;
	}
}
