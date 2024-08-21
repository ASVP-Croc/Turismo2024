package com.speriamochemelacavo.turismo2024.elements;

import java.util.stream.Stream;

public class Contest extends Element {
	private final Element element;
	
	public Contest(String description, Element element, Integer id) {
		super(description, id);
		this.element = element;
	}
	
	public Element getElement() {
		return element;
	}
	
	public Stream<Content> getContents(){
		return super.getContents();
	}
	
	public String getDescription() {
		return super.getDescritpion();
	}

	public Content addContent(Content content){
		return super.addContent(content);
	}
	
	public Integer getId() {
		return super.getId();
	}
	
	public Content getContent(Integer id) {
		return super.getContent(id);
	}

	public Content deleteContent(Integer id) {
		return super.deleteContent(id);
		
	}


}
