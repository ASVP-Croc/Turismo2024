package com.speriamochemelacavo.turismo2024.models.elements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;
import com.speriamochemelacavo.turismo2024.models.users.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "contents")
public class Content {

	@Id
	private int id;
	private String text;
	@ManyToOne
	private Element referenced;
	@ManyToOne
	private AuthenticatedUser creator;
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
	
