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

	public String getText() {
		return text;
	}
	
	public int getId() {
		return id;
	}
	
	public Role getCreator() {
		return creator.getRole();
	}
	
	public void setVisibility() {
		isPublished=true;
	}
	
	public boolean getVisibility() {
		return isPublished;
	}
	
	public Element getReferencedElement() {
		return referenced;
	}
	
}
	
