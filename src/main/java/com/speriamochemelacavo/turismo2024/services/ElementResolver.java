package com.speriamochemelacavo.turismo2024.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.speriamochemelacavo.turismo2024.models.elements.Element;

@Service
public abstract class ElementResolver<T extends Element> implements Resolver<T>{
	
	protected ObjectMapper mapper = new ObjectMapper();

    public T resolveElement(String element) throws JsonProcessingException{
        LinkedHashMap<String, Object> result = (LinkedHashMap<String, Object>)mapper.readValue(element, LinkedHashMap.class);
        return elementResolver(result);
    }

    public List<T> resolveElements(String elements) throws JsonProcessingException{
        List<T> elementsToReturn = new ArrayList<>();
        ArrayList<LinkedHashMap<String, Object>> result = (ArrayList<LinkedHashMap<String, Object>>)mapper.readValue(elements, ArrayList.class);
        result.stream().forEach(l-> {elementsToReturn.add(elementResolver(l));});
        return elementsToReturn;
    }

    protected abstract T elementResolver(LinkedHashMap<String, Object> element);
}
