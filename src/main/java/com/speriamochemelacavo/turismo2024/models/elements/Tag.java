package com.speriamochemelacavo.turismo2024.models.elements;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "Tags", indexes = {
	    @Index(name = "idx_value", columnList = "value", unique = true)})
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String value;
	
	public Tag() {
	}
	
	public Tag(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
