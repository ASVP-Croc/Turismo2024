package com.speriamochemelacavo.turismo2024.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.repository.ElementRepository;

@Service
public class ContentsService extends ElementsService<Content>{
	
	public void setReferenced(int contentId, Element referencedElement) {
		super.findById(contentId).setReferenced(referencedElement);
	}
	
}
