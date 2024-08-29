package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Elements")
@Component
public class Element {
	
	@Id
	private int id;
	private String description;
	@ManyToOne
	private AuthenticatedUser author;
	private boolean isPublished;
	@OneToMany(mappedBy = "referenced", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Content> myContents;
	
	public Element() {
		
	}
	
	public Element(int id, String description) {
		this.id = id;
		this.description = description;
		this.isPublished = false;
		this.myContents = new ArrayList<Content>();		
	}

	public Integer getId() {
		return id;
	}
	
	public boolean addContent(Content content) {
		return myContents.add(content);
	}
	
	public boolean deleteContent(int id) {
		return myContents.remove(id) != null;
	}
	
	public Stream<Content>getContents() {
		return myContents.stream();
	}

	public Content getContent(Integer id) {
		return myContents.get(id);
	}

	public int getAuthorId() {
		return this.author.getId();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AuthenticatedUser getAuthor() {
		return author;
	}

	public void setAuthor(AuthenticatedUser author) {
		this.author = author;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	public List<Content> getMyContents() {
		return myContents;
	}

	public void setMyContents(List<Content> myContents) {
		this.myContents = myContents;
	}
	

}
