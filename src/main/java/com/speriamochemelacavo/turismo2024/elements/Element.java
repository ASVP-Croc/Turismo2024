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
	
	public Element(String description, AuthenticatedUser author) {
		this.description = description;
		this.author = author;
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
