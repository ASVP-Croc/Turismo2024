package com.speriamochemelacavo.turismo2024.services;

import com.speriamochemelacavo.turismo2024.models.elements.Element;

public interface ResolverService<T extends Element> {

	public T getElement();
	
}
