package com.speriamochemelacavo.turismo2024.models.elements.content;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.elements.category.ContentType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.elements.category.ElementTypology;
import com.speriamochemelacavo.turismo2024.models.users.User;

import java.time.LocalDateTime;
import java.util.Date;

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
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ElementWithContents referenced;
	private String pathToResource;

	@Enumerated(EnumType.STRING)
	private ContentType contentType;

	public Content() {
		super();
		this.typology = ElementTypology.CONTENT;
	}

//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	public Content(String name, String description, User author, LocalDateTime createdAt, LocalDateTime updatedAt, ElementWithContents referenced, String pathToResource) {
		super(name, description, author);
		this.createdAt = new Date();
		this.updatedAt = new Date();
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

	public String getPathToResource() {
		return pathToResource;
	}

	public void setPathToResource(String pathToResource) {
		this.pathToResource = pathToResource;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}
}
	
