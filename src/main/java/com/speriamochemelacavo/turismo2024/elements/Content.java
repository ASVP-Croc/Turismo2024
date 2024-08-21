package com.speriamochemelacavo.turismo2024.elements;

import com.speriamochemelacavo.turismo2024.users.Role;

public class Content {
	private final String text;
	private static Integer generalId = 0;
	private final Integer id;
	private final Role creator;
	private boolean published;
	private final Element referenced;

	public Content(String text, Role role, Element element) {
		this.text = text;
		this.id= generalId++;
		this.creator = role;
		this.published=false;
		this.referenced=element;
	}

	public String getText() {
		return text;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Role getCreator() {
		return creator;
	}
	
	public void setVisibility() {
		published=true;
	}
	
	public boolean getVisibility() {
		return published;
	}
	
	public Element getReferencedElement() {
		return referenced;
	}
	
}
	
