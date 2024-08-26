package com.speriamochemelacavo.turismo2024.elements;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

import com.speriamochemelacavo.turismo2024.users.AuthenticatedUser;

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
public abstract class Element implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "description")
	private String description;
	@ManyToOne
	@JoinColumn(name = "author")
	private AuthenticatedUser author;
	@Column(name = "isPublished")
	private boolean isPublished;
	@OneToMany(mappedBy = "referenced", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Content> myContents;
	
	public Element() {
		
	}
	
	public Element(String text, Integer authorId) {
		this.description = text;
		this.isPublished=false;
	}
	
	public boolean addContent(Content content) {
		return myContents.add(content);
	}
	
	public Stream<Content>getContents() {
		return myContents.stream();
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
		isPublished=true;
	}

	public boolean getVisibility() {
		return isPublished;
	}

	public int getAuthorId() {
		return this.author.getId();
	}
	
	public boolean deleteContent(int id) {
		return myContents.remove(id) != null;
	}

}
