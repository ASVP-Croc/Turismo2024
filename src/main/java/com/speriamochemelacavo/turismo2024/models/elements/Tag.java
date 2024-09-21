package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Component
@Entity
@Table(name = "tags"
		,indexes = {
        @Index(name = "idx_tagName", columnList = "tagName", unique = true)}
)
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id = 0;
	@Column(name = "tagName", nullable = false, unique = true)
	private String tagName;

	@ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ElementWithContents> elementsTagged = new HashSet<>();

	public Tag() {
		
	}
	
	public <T extends ElementWithContents> Tag(String tagName) {
		this.tagName = tagName;
	}

	public <T extends ElementWithContents> Tag(String tagName, T elementsTagged) {
		this.tagName = tagName;
		this.elementsTagged.add(elementsTagged);
	}
	
	public <T extends ElementWithContents> Tag(String tagName, List<T> elementsTagged) {
		this.tagName = tagName;
		this.elementsTagged.addAll(elementsTagged);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}
	
	public void setTagName(String value) {
		this.tagName = value;
	}

	public Set<ElementWithContents> getElements() {
		return elementsTagged;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Tag tag1 = (Tag) obj;
		return (this.getTagName().equals(tag1.getTagName()) && this.getId()==tag1.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tagName, elementsTagged);
	}	
	
	@Override
	public String toString() {
		String toReturn = "";
		toReturn = toReturn + ("Tag [nome=" + tagName + ", elementsTagged=");
		toReturn = toReturn + (" { ");
		String temp = "";
		elementsTagged.stream().forEach(e -> {
			temp.concat(e.toString() + "\n");
			});
		toReturn = toReturn + temp;
		toReturn = toReturn + " }" + "]";
		return toReturn;
	}
}
