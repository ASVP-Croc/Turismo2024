package com.speriamochemelacavo.turismo2024.services;

import java.util.ArrayList;
import java.util.List;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;

public class POIsService {
	
private final List<PointOfInterest> POIs = new ArrayList<>();
	
	public POIsService () {
		
	}

	public List<PointOfInterest> findAll() {
		return POIs;
	}
	
	public PointOfInterest findById(int POIToFindId) {
		return POIs.stream().filter(user -> user.getId() == POIToFindId).findFirst().get();
	}

	public void addPOI(PointOfInterest POIToAdd) {
		POIs.add(POIToAdd);
	}
	

	public void updatePOI(PointOfInterest newPOI) {
		PointOfInterest POIToUpdate = findById(newPOI.getId());
		POIToUpdate.setAuthor(newPOI.getAuthor());
		POIToUpdate.setDescription(newPOI.getDescription());
		POIToUpdate.setLatitude(newPOI.getLatitude());
		POIToUpdate.setLongitude(newPOI.getLongitude());
		POIToUpdate.setPublished(newPOI.isPublished());
	}
	
	public void deletePOIById(int POIToDeleteId) {
		POIs.removeIf(POI -> POI.getId() == POIToDeleteId);
	}
	
	public List<Content> getContentsById(int POIToGetContentsId){
		return findById(POIToGetContentsId).getMyContents();
	}
	
	
}

