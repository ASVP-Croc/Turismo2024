package com.speriamochemelacavo.turismo2024.elements;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class Element {
	
	private int id;
	private String description;
	private Integer author;
	private boolean published;
	private Map<Integer, Content> myContents;
	
	public Element(String text, Integer authorId) {
		this.description = text;
		this.myContents = new HashMap<>();
		this.published=false;
		this.author = authorId;
	}
	
	public Content addContent(Content content) {
		return myContents.put(content.getId(), content);
	}
	
	public Stream<Content>getContents() {
		return myContents.values().stream();
	}
	
	public String getDescritpion() {
		return description;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Content getContent(Integer id) {
		return myContents.get(id);
	}
	
	public void setVisibility() {
		published=true;
	}

	public boolean getVisibility() {
		return published;
	}

	public Integer getAuthorId() {
		return this.author;
	}
	
	public Content deleteContent(Integer id) {
		return myContents.remove(id);
	}

}
