package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class ElementWithContents extends Element {
	
	@JsonProperty("city")
	private String city;
	@JsonProperty("postcode")
	private String postalcode = "";
	@OneToMany(mappedBy = "referenced", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Content> myContents = new HashSet<>();
	
	public ElementWithContents() {
		super();
	}

	public ElementWithContents(String name, String description, User author, List<Content> contents, String city, String postalcode) {
		super(name, description, author);
		this.city = city;
		this.postalcode = postalcode;
		this.myContents.addAll(contents);
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public Set<Content> getMyContents() {
		return myContents;
	}
	
	public void setMyContents(Set<Content> contents) {
		myContents.addAll(contents);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
