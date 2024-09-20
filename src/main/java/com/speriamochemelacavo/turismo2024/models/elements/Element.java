package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

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
public class Element {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonProperty("name")
	private String name;
	private String description;
	@ManyToOne(cascade = CascadeType.ALL)
	private User author;
	
	protected String typology;
	//
	private boolean isValidated = false;
	//utile? il report lo vedo solo con la notifica, non modifica la visibilità
	private boolean isReported = false;
	
	public Element() {
		
	}

//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	public Element(String name, String description, User author) {
		this.name = name;
		this.description = description;
		this.author = author;	
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getTypology() {
		return typology;
	}
	

	public boolean isValidated() {
		return isValidated;
	}

	public void setValidation(boolean isValidated) {
		this.isValidated = isValidated;
	}
	public boolean isReported() {
		return isReported;
	}

	public void setReported(boolean isReported) {
		this.isReported = isReported;
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

	@Override
	public String toString() {
		return "Element [name=" + name + "]";
	}
}
