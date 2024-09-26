package com.speriamochemelacavo.turismo2024.models.elements;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.speriamochemelacavo.turismo2024.models.elements.category.ElementTypology;
import com.speriamochemelacavo.turismo2024.models.notifications.Notificable;
import com.speriamochemelacavo.turismo2024.models.users.User;

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
@Inheritance(strategy = InheritanceType.JOINED)
public class Element extends Notificable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id = 0;
	@JsonProperty("name")
	private String name;
	private String description;
	@ManyToOne(cascade = CascadeType.ALL)
	private User author;
	@Enumerated(EnumType.STRING)
	protected ElementTypology typology;
	@Enumerated(EnumType.STRING)
	private ElementStatus status = ElementStatus.PENDING;

	private boolean isReported = false;
	
	public Element() {
		
	}

	public Element(String name, String description, User author) {
		this.name = name;
		this.description = description;
		this.author = author;
	}

	public int getId() {
		return id;
	}
	
	public String getIdToString() {
		return String.valueOf(getId());
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

	public ElementTypology getTypology() {
		return typology;
	}

	@Override
	public ElementStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(ElementStatus status) {
		this.status = status;
	}
	
	public boolean isReported() {
		return isReported;
	}

	public void setReported(boolean isReported) {
		this.isReported = isReported;
	}
	
	@Override
	public boolean equals(Object elementToEquals) {
		if (this == elementToEquals) return true;
		if (elementToEquals != null && getClass() == elementToEquals.getClass()) {
			Element toEquals = (Element) elementToEquals;
			return (toEquals.getId() == this.getId());
		}
		return false;
	}

	@Override
	public String toString() {
		return "Element [name=" + name + "]";
	}
}
