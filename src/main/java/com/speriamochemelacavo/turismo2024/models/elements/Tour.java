package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class Tour extends Element{
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<PointOfInterest> myPOIs = new ArrayList<>();

	public List<PointOfInterest> getMyPOIs() {
		return myPOIs;
	}
	
//	//costruttore di copia
//	public Tour(Tour other,  AuthenticatedUser author) {
//	    super(other.getDescription(), author);
//	    this.myPOIs = new ArrayList<>(other.myPOIs);
//	}
	
	

}
