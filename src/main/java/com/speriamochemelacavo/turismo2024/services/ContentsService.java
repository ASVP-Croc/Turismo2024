package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import com.speriamochemelacavo.turismo2024.repository.ContentRepository;
import com.speriamochemelacavo.turismo2024.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;

@Service
public class ContentsService extends ElementsService<Content>{

	@Autowired
	private ContentRepository contentRepository;
	
	private boolean isContentsLoaded;

	public ContentsService() {
		super();
	}
	
	@Override
	public List<Content> findAll() {
		return contentRepository.findAll();
	}



	public Content add(Content contentToAdd, ElementWithContents referenced, String pathToResource) {
		contentToAdd.setReferenced(referenced);
		contentToAdd.setPathToResource(pathToResource);
		return super.add(contentToAdd);
	}

	//lo fa l'elementWithContentsSevice
//	public Content addContentToPOI(int id, Content contentToAdd) throws ElementNotFoundException {
//		PointOfInterest poi = poiRepository.findById(id).orElseThrow(() -> new ElementNotFoundException());
//		contentToAdd.setReferenced(poi);
//		contentRepository.save(contentToAdd);
//		return contentToAdd;
//	}


	public boolean isLoaded() {
		return isContentsLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		isContentsLoaded = isLoaded;
	}
}
