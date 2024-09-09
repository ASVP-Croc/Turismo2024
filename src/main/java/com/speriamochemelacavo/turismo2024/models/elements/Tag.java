package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "tags", indexes = {
	    @Index(name = "idx_tag", columnList = "tag", unique = true)})
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tag;
	@ManyToMany
	private List<Element> elementsTagged;
	
	public int getId() {
		return id;
	}

	public Tag() {
	}
	
	public Tag(String value) {
		this.tag = value;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String value) {
		this.tag = value;
	}

	public List<Element> getElements() {
		return elementsTagged;
	}
	
	public void addElement(Element element) {
		getElements().add(element);
	}

	public void setElements(List<Element> elements) {
		this.elementsTagged = elements;
	}
}
