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
import jakarta.persistence.OneToOne;
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
	@OneToOne(targetEntity = Element.class, cascade = CascadeType.MERGE)
	private Element notificationObject;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<User> recipientUsers = new ArrayList<User>();
	private boolean isRead;
	
	public Notification() {
		
	}
	
	public Notification(String title, String message, User author, Element notificationObject, User RecipientUser) {
		this.title = title;
		this.message = message;
		this.author = author;
		this.notificationObject = notificationObject;
		this.recipientUsers.add(RecipientUser);
		setRead(false);
	}
	
	public Notification(String title, String message, User author, Element notificationObject, List<User> RecipientUsers) {
		this.title = title;
		this.message = message;
		this.author = author;
		this.notificationObject = notificationObject;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Element getNotificationObject() {
		return notificationObject;
	}
	
	public void setNotificationObject(Element notificationObject) {
		this.notificationObject = notificationObject;
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
	
	@Override
	public boolean equals(Object notificationToEquals) {
		if (notificationToEquals instanceof Notification) {
			Notification toEquals = (Notification) notificationToEquals;
			if (toEquals.getId() == this.getId()) {
				return true;
			}
		}
		return false;
	}
}
