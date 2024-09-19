package com.speriamochemelacavo.turismo2024.services;

import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.models.elements.Content;
import java.util.List;

import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public class POIsService extends ElementsWithContentsService<PointOfInterest> {
	
	private boolean isPoisLoaded;

	public POIsService(){
		super();
	}

	public void add(PointOfInterest POIToAdd, User author, List<Tag> tags, List<Content> contentToAdd, Address address) {
		POIToAdd.setAddress(address);
		super.add(POIToAdd, author, tags, contentToAdd);
	}

	@Override
	public boolean isLoaded() {
		return isPoisLoaded;
	}

	@Override
	public void setLoaded(boolean isLoaded) {
		isPoisLoaded = isLoaded;
	}
}

