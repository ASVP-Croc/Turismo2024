package com.speriamochemelacavo.turismo2024.elements;

import com.speriamochemelacavo.turismo2024.users.AuthenticatedUser;

import jakarta.persistence.Entity;

@Entity
public class Contest extends Element {
	
	public Contest() {
	
	}
	
	public Contest(String description, AuthenticatedUser author) {
		super(description, author);
	}

}
