package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public class ToursService extends ElementsWithContentsService<Tour>{
	
	public void add(Tour tourToAdd, User author, List<Tag> tags, List<PointOfInterest> POIToAdd) {
		tourToAdd.getMyPOIs().addAll(POIToAdd);
		super.add(tourToAdd, author, tags);
	}

	@Override
	public void deleteById(Integer id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Tour tourToDelete) {
		super.delete(tourToDelete);
	}

	@Override
	public void deleteAll(List<Tour> toursToDelete) {
		super.deleteAll(toursToDelete);
	}
}
