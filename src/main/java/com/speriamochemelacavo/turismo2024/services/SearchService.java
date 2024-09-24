package com.speriamochemelacavo.turismo2024.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class SearchService {

    @Autowired
    private NominatimService nominatimService;

    @Autowired
    private POIResolver POIResolver;

    @Autowired
    private TagsService tagService;

    public List<ElementWithContents> searchElementsSiteByString(String tag){
        List<ElementWithContents> toReturn = new ArrayList<>();
        Set<String> firstSplit = tagService.split(tag.toUpperCase()).stream().collect(Collectors.toSet());
        firstSplit.stream()
        	.forEach(t ->{
        		try {
					toReturn.addAll(tagService.findByTag(t).getElements());
				} catch (SQLIntegrityConstraintViolationException e) {
					e.printStackTrace();
				}
        });
        sortListByOccurrences(toReturn);
        return toReturn;
    }

    public List<PointOfInterest> searchElementsOSMWithDetails(String amenity, String street, String houseNumber, String postalCode) {
        List<PointOfInterest> toReturn = new ArrayList<>();
        try {
			toReturn.addAll(POIResolver.resolveElements(
			                nominatimService.getInfoFromParameter(amenity, street + " " + houseNumber, postalCode))
			);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return toReturn;
    }

    public List<PointOfInterest> searchElementsOSMWithQuery(String query) {
        List<PointOfInterest> toReturn = new ArrayList<>();
        try {
			toReturn.addAll(POIResolver.resolveElements(nominatimService.getInfoFromQuery(query)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return toReturn;
    }

    private List<ElementWithContents> sortListByOccurrences(List<ElementWithContents> elemtsList) {
        Map<ElementWithContents, Integer> occurrences = new HashMap<>();
        
        for (ElementWithContents element : elemtsList) {
            occurrences.put(element, occurrences.getOrDefault(element, 0) + 1);
        }
        
        List<Map.Entry<ElementWithContents, Integer>> entries = new ArrayList<>(occurrences.entrySet());
        entries.sort(Comparator.comparingInt(Map.Entry::getValue));
        List<ElementWithContents> result = new ArrayList<>();
        
        for (Map.Entry<ElementWithContents, Integer> entry : entries) {
            result.add(entry.getKey());
        }
        
        return result;
    }
}
