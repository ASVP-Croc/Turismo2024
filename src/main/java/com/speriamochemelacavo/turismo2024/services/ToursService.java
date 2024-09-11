package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public class ToursService extends ElementsWithContentsService<Tour>{
	
	@Override
	public void add(Tour tourToAdd, User author) {
		super.add(tourToAdd, author);
	}

	@Override
	public void add(Tour tourToAdd, User author, List<Tag> tags) {
		super.add(tourToAdd, author, tags);
	}
	
	public void add(Tour tourToAdd, User author, List<Tag> tags, PointOfInterest POIToAdd) {
		tourToAdd.getMyPOIs().add(POIToAdd);
		super.add(tourToAdd, author, tags);
	}
	
	public void add(Tour tourToAdd, User author, List<Tag> tags, List<PointOfInterest> POIToAdd) {
		tourToAdd.getMyPOIs().addAll(POIToAdd);
		super.add(tourToAdd, author, tags);
	}
}
