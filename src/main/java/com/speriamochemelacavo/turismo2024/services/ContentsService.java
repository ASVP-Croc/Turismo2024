package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import com.speriamochemelacavo.turismo2024.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;

@Service
public class ContentsService extends ElementsService<Content>{

	private boolean isContentsLoaded;

	public ContentsService() {
		super();
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
