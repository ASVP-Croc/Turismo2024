package com.speriamochemelacavo.turismo2024.models.elements;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.elements.category.ElementTypology;
import com.speriamochemelacavo.turismo2024.models.users.User;

import java.time.LocalDateTime;

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

	/*
	Questo commento dovrà essere cancellato, sono le modifiche per il modello
	 */
	private String title;

	private String url;

	private String note;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ElementWithContents referenced;
	private String pathToResource;

	public Content() {
		super();
		this.typology = ElementTypology.CONTENT.toString();
	}

//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	public Content(String name, String description, User author, String city, String postcode, String title, String url, String note, LocalDateTime createdAt, LocalDateTime updatedAt, ElementWithContents referenced, String pathToResource) {
		super(name, description, author, city, postcode);
		this.title = title;
		this.url = url;
		this.note = note;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.referenced = referenced;
		this.pathToResource = pathToResource;
		this.typology = ElementTypology.CONTENT.toString();
	}

	public ElementWithContents getReferenced() {
		return referenced;
	}

	public void setReferenced(ElementWithContents referenced) {
		this.referenced = referenced;
	}

	public String getPathToResource() {
		return pathToResource;
	}

	public void setPathToResource(String pathToResource) {
		this.pathToResource = pathToResource;
	}
}
	
