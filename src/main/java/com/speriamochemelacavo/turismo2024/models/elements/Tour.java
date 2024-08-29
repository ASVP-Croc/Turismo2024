package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class Tour extends Element{
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<PointOfInterest> myPOIs = new ArrayList<>();
	
	public Tour() {
		super();
	}

	public Tour(int id, String description) {
		super(id, description);
	}

	public List<PointOfInterest> getMyPOIs() {
		return myPOIs;
	}
}
