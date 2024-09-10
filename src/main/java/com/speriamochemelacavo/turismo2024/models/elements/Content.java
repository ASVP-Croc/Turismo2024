package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
	private Element referenced;
	private String pathToResource;

	public Content() {
		super();
	}
	
	public Content(String name, String description) {
		super(name, description);
	}

	public Element getReferenced() {
		return referenced;
	}

	public void setReferenced(Element referenced) {
		this.referenced = referenced;
	}

	public String getResource() {
		return pathToResource;
	}

	public void setResource(String resource) {
		this.pathToResource = resource;
	}
}
	
