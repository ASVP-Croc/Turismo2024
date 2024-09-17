package com.speriamochemelacavo.turismo2024.models.elements;

import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

/**
 * Rappresenta un {@link Element Elemento} contenente una risorsa multimediale di qualsiasi natura(un'immagine, un video, un file, etc.) collegato ad uno e un solo altro {@link Element Elemento} contenitore.
 * Contiene il link di riferimento alla risorsa salvata.
 *
 * @field {@link Element} referenced: L'elemento padre che contiene la risorsa.
 * @field String resource: il path riferito al file.
 * 
 */

@Component
@Entity
public class Content extends Element{
	
	@OneToOne(cascade = CascadeType.ALL)
	private ElementWithContents referenced;
	private String pathToResource;

	public Content() {
		super();
	}

//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	public Content(String name, String description, User author, String city, String postcode, ElementWithContents referenced, String pathToResource) {
		super(name, description, author, city, postcode);
		this.referenced = referenced;
		this.pathToResource = pathToResource;
		this.typology = ElementTypology.CONTENT;
	}

	public ElementWithContents getReferenced() {
		return referenced;
	}

	public void setReferenced(ElementWithContents referenced) {
		this.referenced = referenced;
	}

	public String getResource() {
		return pathToResource;
	}

	public void setResource(String resource) {
		this.pathToResource = resource;
	}
}
	
