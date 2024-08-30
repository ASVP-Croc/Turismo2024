package com.speriamochemelacavo.turismo2024.models.elements;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;

@Component
@Entity
public class Contest extends Element {

	
	
	public Contest() {
		super();
	}

	public Contest(int id, String description) {
		super(id, description);
	}
	
}
