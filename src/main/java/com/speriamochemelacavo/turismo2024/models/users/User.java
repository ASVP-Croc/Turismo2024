package com.speriamochemelacavo.turismo2024.models.users;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.notifications.Notification;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Rappresenta un qualsiasi {@link User Utente} registrato.
 * Principalmente, gli {@link User Utenti} registrato si differenziano tra loro grazie ad un Id e al {@link Role Ruolo} che ricoprono all'interno della piattaforma.
 * Il {@link Role Ruolo} permette loro di utilizzare diverse funzionalità che la piattaforma offre (creazione di {@link PointOfInterest Punti di Interesse}, validazione dei vari {@link Element Elementi}, modifica delle autorizzazioni, etc.).
 * Hanno la possibilità di ricevere delle {@link Notification Notifiche} che li avvertono di ogni cambiamento che li riguardi e posso salvare gli {@link Element Elementi} che ritengono interessanti, per poi ritrovarli facilmente in seguito.
 * Devono avere un nome, un cognome, un username, una email e un numero di telefono associati.
 * Alla loro creazione, ogni {@link User Utente} avrà il {@link Role Ruolo} di {@link Role.Tourist Turista} e un Id assegnato automaticamente dalla Piattaforma.
 *
 * @field int id: l'id assegnato all'{@link User Utente} dopo l'inserimento nel Database.
 * @field String name: il nome dell'{@link User Utente}.
 * @field String surname: il cognome dell'{@link User Utente}.
 * @field String userName: l'username dell'{@link User Utente}.
 * @field String email: l'email di registrazione dell'{@link User Utente}.
 * @field String phoneNumber: il numero di telefono dell'{@link User Utente}.
 * @field {@link Role} role: il {@link Role Ruolo} dell'{@link User Utente}.
 * @field {@link List} notifications: la lista delle notifiche che hanno come destinatoario l'{@link User Utente}.
 * @field {@link List} savedElements: la lista degli {@link Element Elementi} salvati dall'{@link User Utente}.
 * 
 */

@Component
@Entity
@Table(name = "users", indexes = {
	    @Index(name = "idx_userName", columnList = "user_name", unique = true),
	    @Index(name = "idx_role", columnList = "role", unique = false)})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String surname;
	private String userName;
	private String email;
	private String phoneNumber;
	private String address;
	private String comune;
	private int CAP;
	@Enumerated(EnumType.STRING)
	private Role role;
	@ManyToMany(mappedBy = "recipientUsers", cascade = CascadeType.ALL)
	private List<Notification> notifications;
	@OneToMany
	private List<Element> savedElements;

	public User() {}
	
	public User(String name, String surname, String userName, String email, String phoneNumber, String address, String comune, int CAP, Role role) {
		this.name = name;
		this.surname = surname;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.comune = comune;
		this.CAP = CAP;
		this.role = role;
		this.notifications = new ArrayList<Notification>();
		this.savedElements = new ArrayList<Element>();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public int getCAP() {
		return CAP;
	}

	public void setCAP(int cAP) {
		CAP = cAP;
	}
}
		
	