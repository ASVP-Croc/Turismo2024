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

	public void add(PointOfInterest POIToAdd, User author, List<Tag> tags, List<Content> contentToAdd, Address address) {
		POIToAdd.setAddress(address);
		super.add(POIToAdd, author, tags, contentToAdd);
	}
}

