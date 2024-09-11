package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class ElementWithContents extends Element {
	
	@OneToMany(mappedBy = "referenced", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Content> myContents;
	
	public ElementWithContents() {
		super();
	}
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	public ElementWithContents(String name, String description, User author, String city, int postcode, List<Content> contents) {
		super(name, description, author, city, postcode);
		this.myContents = contents;
	}
	
	public List<Content> getMyContents() {
		return myContents;
	}
}
