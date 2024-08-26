package com.speriamochemelacavo.turismo2024.elements;

import com.speriamochemelacavo.turismo2024.users.AuthenticatedUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "message")
	private String message;
	@Column(name = "object")
	private Element object;
	@ManyToOne
	@JoinColumn(name = "author")
	private AuthenticatedUser authorUser;
	@ManyToOne
	@JoinColumn(name = "recipient")
	private AuthenticatedUser recipientUser;
	
	
	public Notification(String text) {
	}
	
	public String getMessage() {
		return message;
	}
}
