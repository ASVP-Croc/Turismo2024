package com.speriamochemelacavo.turismo2024.elements;

import com.speriamochemelacavo.turismo2024.users.AuthenticatedUser;
import com.speriamochemelacavo.turismo2024.users.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contents")
public class Content {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "text")
	private String text;
	@ManyToOne
	@JoinColumn(name = "referencedElement")
	private Element referenced;
	@ManyToOne
	@JoinColumn(name = "creator")
	private AuthenticatedUser creator;
	@Column(name = "isPublished")
	private boolean isPublished;

	public Content() {
		
	}
	
	public Content(String text, Role role, Element element) {
		this.text = text;
		this.creator.setRole(role);
		this.isPublished=false;
		this.referenced=element;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Element getReferenced() {
		return referenced;
	}

	public void setReferenced(Element referenced) {
		this.referenced = referenced;
	}

	public AuthenticatedUser getCreator() {
		return creator;
	}

	public void setCreator(AuthenticatedUser creator) {
		this.creator = creator;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	
}
	
