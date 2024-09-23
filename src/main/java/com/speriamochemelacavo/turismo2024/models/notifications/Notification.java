package com.speriamochemelacavo.turismo2024.models.notifications;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "notifications")
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String message;
	@ManyToOne
	private User author;
	@ManyToOne(cascade = CascadeType.ALL)
	private Element notificationObject;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	        name = "notifications_users",
	        joinColumns = @JoinColumn(name = "notification_id"),
	        inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> recipientUsers = new HashSet<>();
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
	
	public Set<User> getRecipientUsers() {
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
		if (this == notificationToEquals) return true;
		if (notificationToEquals != null && getClass() == notificationToEquals.getClass()) {
			Notification toEquals = (Notification) notificationToEquals;
			return (this.getId() == toEquals.getId());
		}
		return false;
	}
}
