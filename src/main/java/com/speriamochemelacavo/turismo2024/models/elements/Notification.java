package com.speriamochemelacavo.turismo2024.models.elements;

import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "notifications")
public class Notification {
	
	@Id
	private int id;
	private String message;
	@ManyToOne
	private Element object;
	@ManyToOne
	private AuthenticatedUser recipientUser;
	
	public Notification() {
		
	}
	
	public Notification(String text) {
		this.message = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Element getObject() {
		return object;
	}

	public void setObject(Element object) {
		this.object = object;
	}

	public AuthenticatedUser getRecipientUser() {
		return recipientUser;
	}

	public void setRecipientUser(AuthenticatedUser recipientUser) {
		this.recipientUser = recipientUser;
	}
	
}
