package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.repository.POIRepository;

@Service
public class POIsService extends ElementsService<PointOfInterest> {
	

	@Autowired
	POIRepository poiRepository;
	
	@Autowired
	ContentsService contentService;

	
	
	public void addPOI(PointOfInterest poi) {
		
		updatePOI(poi);
	}
	
	public void addPOIs(List<PointOfInterest> poiToAdd) {
		poiRepository.saveAll(poiToAdd);
	}
	
	public void addContentToPOIById(Content content, int poiById) {
		PointOfInterest poiToUpdate = findById(poiById);
		contentService.addContent(content);
	}
	
	public List<PointOfInterest> findAll() {
		return poiRepository.findAll();
	}
	
	public List<Content> getPOIContentsById(int poiId){
		return findById(poiId).getMyContents();
	}
	
	public void updatePOI(PointOfInterest poi) {
		poiRepository.save(poi);
	}
	
	
	public void deletePOIById(int poiToDeleteId) {
		poiRepository.deleteById(poiToDeleteId);
	}
	
	public void deleteContentToPOIById(int poiById, int contentById) {
		PointOfInterest poiToUpdate = findById(poiById);
		poiToUpdate.getContent(contentById);
	}
		
}

