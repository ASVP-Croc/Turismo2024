package com.speriamochemelacavo.turismo2024.services;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.security.AccountSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class SearchService {

    @Autowired
    private AccountSecurity accountSecurity;

    @Autowired
    private UsersService userService;

    @Autowired
    private NominatimService nominatimService;

    @Autowired
    private ElementResolver<PointOfInterest> POIResolver;

    @Autowired
    private TagsService tagService;

    public List<Element> searchElementsSite(String tag){
        List<Element> toReturn = new ArrayList<>();
        String tagClean = tag.replaceAll("\\s*,\\s*", ",");
        List<String> tagValuesList = Arrays.stream(tagClean.split("[\\s,]+")).filter(t -> !t.isEmpty()).collect(Collectors.toList());
        tagValuesList.stream().forEach(t ->{
            if (tagService.findByTag(t) != null) toReturn.addAll(tagService.findByTag(t).getElements());
        });
        sortListByOccurrences(toReturn);
        return toReturn;
    }

    public List<PointOfInterest> searchElementsOSMWithDetails(String amenity, String street, String houseNumber, String postalCode) throws IOException {
        List<PointOfInterest> toReturn = new ArrayList<>();
        toReturn.addAll(POIResolver.resolveElements(
                        nominatimService.getInfoFromParameter(
                                amenity,
                                (street == "" && houseNumber == "") ? "" : street + " " + houseNumber,
                                accountSecurity.isLogged() ? userService.findByUserName(accountSecurity.getLoggedUserName()).getCAP() : postalCode)
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
