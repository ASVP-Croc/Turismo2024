package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

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
	
	public ElementWithContents(String name, String description, List<Integer> tags) {
		super(name, description, tags);
		this.myContents = new ArrayList<Content>();
	}
	
	public List<Content> getMyContents() {
		return myContents;
	}
}
