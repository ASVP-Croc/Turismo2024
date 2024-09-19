package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;

@Service
public abstract class ElementsWithContentsService<T extends ElementWithContents> extends ElementsService<T>{
	
	@Autowired
	ContentsService contentService;
	
	public ElementsWithContentsService(){
		super();
	}

	public void add(T elementsWithContentsToAdd, List<Tag> tags, List<Content> contentToAdd) {
		elementsWithContentsToAdd.getMyContents().addAll(contentToAdd);
		super.add(elementsWithContentsToAdd, tags);
	}

	public abstract boolean isLoaded();

	public abstract void setLoaded(boolean isLoaded);
	
	public void addContentToElement(T element, Content content) {
		//lo facciamo prima o qui?
		content.setReferenced(element);
		
		contentService.add(content);
		element.getMyContents().add(content);
		elementRepository.save(element);
	}
	
	public void deleteContentToElement(T elementWithContent, Content content) {
		elementWithContent.getMyContents().remove(content);
		//devo aggiornare l'elemento?
		elementRepository.save(elementWithContent);
		
		contentService.delete(content);
	}
}
