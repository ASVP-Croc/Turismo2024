package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class ElementWithContents extends Element {
	
	@JsonProperty("city")
	private String city;
	@JsonProperty("postcode")
	private String postcode = "";
	@ManyToMany()
	@JoinTable
	private List<Tag> tags = new ArrayList<>();
	@OneToMany(mappedBy = "referenced", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Content> myContents = new ArrayList<>();
	
	public ElementWithContents() {
		super();
	}
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	public ElementWithContents(String name, String description, User author, List<Content> contents, String city, String postcode) {
		super(name, description, author);
		this.city = city;
		this.postcode = postcode;
		this.myContents.addAll(contents);
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public List<Tag> getTags() {
		return tags;
	}

	public List<Content> getMyContents() {
		return myContents;
	}
	
	public void setMyContents(List<Content> contents) {
		myContents.addAll(contents);
	}
}
