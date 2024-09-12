package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
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
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Element> elementsTagged = new ArrayList<>();

	public Tag() {
	}
	

	public <T extends Element> Tag(String tag, T elementsTagged) {
		this.tag = tag;
		this.elementsTagged.add(elementsTagged);
	}
	
	public <T extends Element> Tag(String tag, List<T> elementsTagged) {
		this.tag = tag;
		this.elementsTagged.addAll(elementsTagged);
	}
	
	public int getId() {
		return id;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Tag tag1 = (Tag) o;
		return id == tag1.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tag, elementsTagged);
	}
}
