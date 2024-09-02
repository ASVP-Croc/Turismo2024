package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;

@Service
public class ContentsService<T extends ElementWithContents> extends ElementsService<Content>{
	
//	public void setReferenced(Content content, T referencedElement) {
//		repository.findById(content.getId()).ifPresent(cntnt->cntnt.setReferenced(referencedElement));
//	}
//	
//	public void addContent(Content content, T element) {
//		addElement(content);
//		setReferenced(content, element);		
//	}	
}
