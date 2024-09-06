package com.speriamochemelacavo.turismo2024.models.elements;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

@Entity
@Table(name = "Elements")
@Component
public abstract class Element {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonProperty("name")
	private String name;
	private String description;
	@ManyToOne(cascade = CascadeType.MERGE)
	private User author;
	@JsonProperty("postcode")
	private int CAP;
	private boolean isPublished;
	
	public Element() {
		
	}
	
	public Element(String name, String description) {
		this.name = name;
		this.description = description;
		this.isPublished = false;	
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

	public int getAuthorId() {
		return this.author.getId();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public int getCAP() {
		return CAP;
	}

	public void setCAP(int CAP) {
		this.CAP = CAP;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}
}
