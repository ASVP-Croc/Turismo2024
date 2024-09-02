package com.speriamochemelacavo.turismo2024.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.repository.ElementWithContentsRepository;

@Service
public abstract class ElementsWithContentsService<T extends ElementWithContents> extends ElementsService<T>{
	
	@Autowired
	ContentsService<PointOfInterest> contentService;
}
