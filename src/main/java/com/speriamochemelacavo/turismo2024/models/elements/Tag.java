package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Component
@Entity
@Table(name = "tags",
		uniqueConstraints = @UniqueConstraint (columnNames = "tag"))
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "tag", nullable = false, unique = true)
	private String tag;

    @ManyToMany(mappedBy = "tags")
    private Set<ElementWithContents> elementsTagged = new HashSet<>();

	public Tag() {
		
	}

	public <T extends ElementWithContents> Tag(String tag, T elementsTagged) {
		this.tag = tag;
		this.elementsTagged.add(elementsTagged);
	}
	
	public <T extends ElementWithContents> Tag(String tag, List<T> elementsTagged) {
		this.tag = tag;
		this.elementsTagged.addAll(elementsTagged);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}
	
	public void setTag(String value) {
		this.tag = value;
	}

	public Set<ElementWithContents> getElements() {
		return elementsTagged;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Tag tag1 = (Tag) o;
		return this.getTag().equals(tag1.getTag());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tag, elementsTagged);
	}	
	
	@Override
	public String toString() {
		String toReturn = "";
		toReturn.concat("Tag [tag=" + tag + ", elementsTagged=");
		elementsTagged.stream().forEach(e -> toReturn.concat(e.toString() + "\n"));
		toReturn.concat(" { " + "}" + "]");
		return toReturn;
	}
}
