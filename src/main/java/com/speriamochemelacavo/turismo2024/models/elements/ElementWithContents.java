package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

public class ElementWithContents extends Element {
	
	@OneToMany(mappedBy = "referenced", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Content> myContents;
	
	public ElementWithContents() {
		super();
	}
	
	public ElementWithContents(String name, String description) {
		super(name, description);
	}
	
	public List<Content> getMyContents() {
		return myContents;
	}
}
