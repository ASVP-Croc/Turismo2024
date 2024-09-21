package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
	        name = "element_tags",
	        joinColumns = @JoinColumn(name = "element_id"),
	        inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tags = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Content> myContents = new HashSet<>();
	
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
	
	public Set<Tag> getTags() {
		return tags;
	}

	public Set<Content> getMyContents() {
		return myContents;
	}
	
	public void setMyContents(Set<Content> contents) {
		myContents.addAll(contents);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
