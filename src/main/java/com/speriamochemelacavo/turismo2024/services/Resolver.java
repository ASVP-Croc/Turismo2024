package com.speriamochemelacavo.turismo2024.services;
/**
 * Questa interfaccia fornisce un metodo per la conversione da una data stringa ad un elemento del modello
 */

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.speriamochemelacavo.turismo2024.models.elements.Element;

public interface Resolver<T extends Element> {

    List<T> resolveElements(String elementsString) throws JsonProcessingException;
	
}
