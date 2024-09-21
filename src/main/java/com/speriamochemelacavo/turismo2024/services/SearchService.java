package com.speriamochemelacavo.turismo2024.services;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class SearchService {
	
	@Autowired
	private LoggedUserDetailService loggedUserService;

    @Autowired
    private NominatimService nominatimService;

    @Autowired
    private POIResolver POIResolver;

    @Autowired
    private TagsService tagService;

    public List<Element> searchElementsSite(String tag){
        List<Element> toReturn = new ArrayList<>();
        Set<String> firstSplit = tagService.split(tag.toUpperCase()).stream().collect(Collectors.toSet());
        firstSplit.stream()
        	.forEach(t ->{
        		try {
					toReturn.addAll(tagService.findByTag(t).getElements());
				} catch (SQLIntegrityConstraintViolationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        });
        sortListByOccurrences(toReturn);
        return toReturn;
    }

    public List<PointOfInterest> searchElementsOSMWithDetails(String amenity, String street, String houseNumber, String postalCode) throws IOException {
        List<PointOfInterest> toReturn = new ArrayList<>();
        toReturn.addAll(POIResolver.resolveElements(
                        nominatimService.getInfoFromParameter(
                                amenity,
                                street + " " + houseNumber,
                                postalCode)
                )
        );
        return toReturn;
    }

    public List<PointOfInterest> searchElementsOSMWithQuery(String query) throws IOException{
        List<PointOfInterest> toReturn = new ArrayList<>();
        toReturn.addAll(POIResolver.resolveElements(nominatimService.getInfoFromQuery(query)));
        return toReturn;
    }

    private List<Element> sortListByOccurrences(List<Element> elemtsList) {
        Map<Element, Integer> occurrences = new HashMap<>();
        
        for (Element element : elemtsList) {
            occurrences.put(element, occurrences.getOrDefault(element, 0) + 1);
        }
        
        List<Map.Entry<Element, Integer>> entries = new ArrayList<>(occurrences.entrySet());
        entries.sort(Comparator.comparingInt(Map.Entry::getValue));
        List<Element> result = new ArrayList<>();
        
        for (Map.Entry<Element, Integer> entry : entries) {
            result.add(entry.getKey());
        }
        
        return result;
    }
}
