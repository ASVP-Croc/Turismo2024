package com.speriamochemelacavo.turismo2024.elements;

import com.speriamochemelacavo.turismo2024.users.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "message")
	private String message;
	@Column(name = "author")
	private User authorUser;
	
	
	public Notification(String text) {
	}
	
	public String getMessage() {
		return message;
	}
	
	
	public String addMotivation(String text) {
		return message+text;
	}
}
