package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Rappresenta la base di ogni oggetto che può essere creato da un {@link User Utente}.
 * È formato da un nome, da una descrizione dell'elemento stesso, dal suo autore e dal CAP del comune di riferimento.
 * Viene inizialmente creato come "non pubblicato". Successivamente potrà essere pubblicato in seguito ad un controllo da parte di un {@link User Utente} con privilegi di validazione.
 *
 * @field int id: l'id dell'elemento all'interno del Database.
 * @field String name: Il titolo dell'elemento.
 * @field String description: La descrizione dell'elemento.
 * @field {@link User} author: L'utente che ha creato questo elemento.
 * @field String CAP: Il CAP del comune in cui si trova l'elemento.
 * @field boolean isPublished: lo stato di pubblicazione in cui si trova l'elemento.
 * 
 */

@Component
@Entity
@Table(name = "elements", indexes = {
	    @Index(name = "idx_name", columnList = "name", unique = true)})
public abstract class Element {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonProperty("name")
	private String name;
	private String description;
	@ManyToMany(mappedBy = "elementsTagged",  cascade = CascadeType.ALL)
	private List<Tag> tags;
	@ManyToOne(cascade = CascadeType.MERGE)
	private User author;
	@JsonProperty("city")
	private String city;
	@JsonProperty("postcode")
	private String postcode;
	private boolean isPublished = false;
	
	public Element() {
		
	}

//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	public Element(String name, String description, User author, String city, String postcode) {
		this.name = name;
		this.description = description;
		this.author = author;
		this.city = city;
		this.postcode = postcode;	
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Tag> getTags() {
		return tags;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
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

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj != null && getClass() == obj.getClass()) {
			Element toCompare = (Element) obj;
			if (toCompare.getId() == this.getId()) {
				return true;
			}
		}
		return false;
	}
}
