package com.speriamochemelacavo.turismo2024.services;

import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.models.elements.Content;
import java.util.List;

import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public class POIsService extends ElementsWithContentsService<PointOfInterest> {
	
	private static boolean isLoaded;
	
	public static boolean isLoaded() {
		return isLoaded;
	}

	public static void setLoaded(boolean isLoaded) {
		POIsService.isLoaded = isLoaded;
	}
	
	public POIsService(){
		super();
	}

	@Override
	public void add(PointOfInterest POIToAdd, User author) {
		super.add(POIToAdd, author);
	}

	@Override
	public void add(PointOfInterest POIToAdd, User author, List<Tag> tags) {
		super.add(POIToAdd, author, tags);
	}
	
	@Override
	public void add(PointOfInterest elementsWithContentsToAdd, User author, List<Tag> tags,
			List<Content> contentToAdd) {
		super.add(elementsWithContentsToAdd, author, tags, contentToAdd);
	}

	public void add(PointOfInterest POIToAdd, User author, List<Tag> tags, List<Content> contentToAdd, Address address) {
		POIToAdd.setAddress(address);
		super.add(POIToAdd, author, tags, contentToAdd);
	}


}

