package com.speriamochemelacavo.turismo2024.models.elements;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

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

	@ManyToOne
	private Element referenced;
	private String resource;

	public Element getReferenced() {
		return referenced;
	}

	public void setReferenced(Element referenced) {
		this.referenced = referenced;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
}
	
