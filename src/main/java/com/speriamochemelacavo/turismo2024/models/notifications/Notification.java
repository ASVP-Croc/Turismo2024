package com.speriamochemelacavo.turismo2024.models.notifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
@Component
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String message;
	@ManyToOne
	private User author;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Element object;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<User> recipientUsers = new ArrayList<User>();
	private boolean isRead;
	
	public Notification() {
		
	}
	
	public Notification(String title, String message, User author, Element object) {
		this.title = title;
		this.message = message;
		this.author = author;
		this.object = object;
		this.recipientUsers.add(object.getAuthor());
		setRead(false);
	}
	
	public Notification(String title, String message, User author, Element object, User RecipientUser) {
		this.title = title;
		this.message = message;
		this.author = author;
		this.object = object;
		this.recipientUsers.add(RecipientUser);
		setRead(false);
	}
	
	public Notification(String title, String message, User author, Element object, List<User> RecipientUsers) {
		this.title = title;
		this.message = message;
		this.author = author;
		this.object = object;
		this.recipientUsers.addAll(RecipientUsers);
		setRead(false);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<User> getRecipientUsers() {
		return recipientUsers;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	
}
