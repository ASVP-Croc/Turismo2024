package com.speriamochemelacavo.turismo2024.elements;

import java.util.stream.Stream;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Contest extends Element {
	
	public Contest(String description, Element element, Integer id) {
		super(description, id);
	}

	public Stream<Content> getContents(){
		return super.getContents();
	}
	
	public String getDescription() {
		return super.getDescription();
	}

	public boolean addContent(Content content){
		return super.addContent(content);
	}
	
	public Integer getId() {
		return super.getId();
	}
	
	public Content getContent(Integer id) {
		return super.getContent(id);
	}

	public boolean deleteContent(Integer id) {
		return super.deleteContent(id);
		
	}


}
