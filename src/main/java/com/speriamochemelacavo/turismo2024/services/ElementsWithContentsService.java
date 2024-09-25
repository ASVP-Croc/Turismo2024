package com.speriamochemelacavo.turismo2024.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;

@Service
public abstract class ElementsWithContentsService<T extends ElementWithContents> extends ElementsService<T>{

	public ElementsWithContentsService(){
		super();
	}

	public abstract boolean isLoaded();

	public abstract void setLoaded(boolean isLoaded);
	
	public void addContent(Content content, T element) {
        element.getMyContents().add(content);
        elementRepository.save(element);
    }
	
}
