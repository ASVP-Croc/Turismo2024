package com.speriamochemelacavo.turismo2024.services;

import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Element;

@Service
public class ContentsService extends ElementsService<Content>{
	
	public void setReferenced(int contentId, Element referencedElement) {
		super.findById(contentId).setReferenced(referencedElement);
	}
	
}
