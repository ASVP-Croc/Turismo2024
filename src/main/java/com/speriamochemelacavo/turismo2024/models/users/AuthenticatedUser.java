package com.speriamochemelacavo.turismo2024.models.users;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.Notification;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "users")
public class AuthenticatedUser {
	
	@Id
	private int id;
	private String name;
	private String surname;
	private String userName;
	private String email;
	private String phoneNumber;
	@Enumerated(EnumType.STRING)
	private Role role;
	@OneToMany(mappedBy = "recipientUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Notification> notifications;
	@ManyToMany
	private List<Element> savedElements;

	public AuthenticatedUser() {}
	
	public AuthenticatedUser(int id, String name, String surname, String userName, String email, String phoneNumber, Role role) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.notifications = new LinkedList<>();
		this.savedElements = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public List<Element> getSavedElements() {
		return savedElements;
	}
	
	public Role getRole() {
	return role;
}

	public void setRole(Role role) {
		this.role = role;
	}
}
		
	