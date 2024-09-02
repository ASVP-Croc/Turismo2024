package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Element;

@Service
public class ContentsService<T extends Element> extends ElementsService<Content>{
	
	
	@Autowired
	ValidationsService validationService;
	
	public void setReferenced(Content content, T referencedElement) {
		repository.findById(content.getId()).ifPresent(cntnt->cntnt.setReferenced(referencedElement));
	}
	
	public void addContent(Content content, T element) {
		addElement(content);
		setReferenced(content, element);
		sendToValidator(findById(content.getId()));
		
	}
	
	public void addContents(List<Content> contentsToAdd) {
		addElements(contentsToAdd);
	}
	
	public void deleteContent(Content content) {
		repository.delete(content);
	}
	
	public void sendToValidator(Content content) {
		validationService.setValidation();
	}
	
	
}
