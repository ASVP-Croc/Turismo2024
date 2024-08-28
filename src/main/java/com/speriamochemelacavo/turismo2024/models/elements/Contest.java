package com.speriamochemelacavo.turismo2024.models.elements;

import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;

import jakarta.persistence.Entity;

//@Entity
@Component
public class Contest extends Element {
	
	public Contest() {
	
	}
	
	public Contest(String description, AuthenticatedUser author) {
		super(description, author);
	}

}
