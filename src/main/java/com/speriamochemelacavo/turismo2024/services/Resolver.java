package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.speriamochemelacavo.turismo2024.models.elements.Element;

public interface Resolver<T extends Element> {

    public List<T> resolveElements(String elementsString) throws JsonProcessingException;
	
}
