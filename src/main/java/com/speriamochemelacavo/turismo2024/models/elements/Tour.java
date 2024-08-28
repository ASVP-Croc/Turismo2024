package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//@Entity
@Component
public class Tour extends Element{
	
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
//	@JoinColumn(name = "tourId", referencedColumnName = "id")
	private final List<PointOfInterest> myPOIs = new ArrayList<>();
	
	public Tour() {
		
	}
	
	public Tour(String description,  AuthenticatedUser author) {
		super(description, author);
	}

	public List<PointOfInterest> getMyPOIs() {
		return myPOIs;
	}
	
//	//costruttore di copia
//	public Tour(Tour other,  AuthenticatedUser author) {
//	    super(other.getDescription(), author);
//	    this.myPOIs = new ArrayList<>(other.myPOIs);
//	}
	
	

}
