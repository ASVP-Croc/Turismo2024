package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import com.speriamochemelacavo.turismo2024.repository.MultimediaMaterialRepository;
import com.speriamochemelacavo.turismo2024.repository.POIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public class ToursService extends ElementsWithContentsService<Tour>{
	
	private boolean isToursLoaded;
	@Autowired
	private MultimediaMaterialRepository multimediaMaterialRepository;

	@Autowired
	private POIRepository poIsRepository;

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
	
	@Override
	public boolean isLoaded() {
		return isToursLoaded;
	}

	@Override
	public void setLoaded(boolean isLoaded) {
		isToursLoaded = isLoaded;
	}
}
