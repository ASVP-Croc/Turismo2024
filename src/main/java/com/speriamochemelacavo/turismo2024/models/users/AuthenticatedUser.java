package com.speriamochemelacavo.turismo2024.models.users;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.Notification;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//@Entity
//@Table(name = "users")
@Component
public class AuthenticatedUser implements GeneralUser {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	@Column(name = "name")
	private String name;
//	@Column(name = "surname")
	private String surname;
//	@Column(name = "username")
	private String userName;
//	@Column(name = "email")
	private String email;
//	@Column(name = "phonenumber")
	private String phoneNumber;
	private Role role = Role.Tourist;
//	@OneToMany(mappedBy = "recipientUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private Queue<Notification> notifications;
//	@ManyToMany
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

//	public Request sendRequest() {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("1: CreaPOI " + "-" + " 2: CreaItinerario " + "-" + " 3: CreaContenuto per Itinerario " + "-"
//				+ " 4: CreaContenuto per POI "+"-"+"5: Aggiungi POI a Itinerario "+"-"+"6: CreaContest "+"-"+"7: Crea Contenuto per Contest "+
//				"-"+"8: Validare Elementi o Contenuti "+"-"+" 9: Visualizza POIs "+"-"+"10: Visualizza Tour "+"-"
//				+"11: Visualizza Contest"+ "12: Cambia Ruolo");
//		Integer input = scanner.nextInt();
//		if (input == 1) {
//			System.out.println("Hai selezionato: CreaPOI");
//			return new Request(this,Action.CreatePOI);
//		} else if (input == 2) {
//			System.out.println("Hai selezionato: CreaItinerario");
//			return new Request(this,Action.CreateTour);
//		} else if (input == 3) {
//			System.out.println("Hai selezionato: CreaContenuto per Itinerario");
//			return new Request(this,Action.CreateContentInTour);
//		} else if (input == 4) {
//			System.out.println("Hai selezionato: CreaContenuto per POI");
//			return new Request(this,Action.CreateContentInPOI);
//		} else if (input == 5) {
//			System.out.println("Hai selezionato: Aggiungi POI a Itinerario");
//			return new Request(this,Action.AddPOIInTour);
//		} else if (input == 6) {
//			System.out.println("Hai selezionato: CreaContest");
//			return new Request(this,Action.CreateContest);
//		} else if (input == 7) {
//			System.out.println("Hai selezionato: CreaContenuto per Contest");
//			return new Request(this,Action.CreateContentInContest);
//		} else if (input == 8) {
//			System.out.println("Hai selezionato: Validare elementi o contenuti");
//			return new Request(this,Action.Validate);
//		} else if (input == 9) {
//			System.out.println("Hai selezionato: VisualizzaPOI");
//			return new Request(this,Action.GetPOIs);
//		} else if (input == 10) {
//			System.out.println("Hai selezionato: VisualizzaItinerario");
//			return new Request(this,Action.GetTours);
//		} else if (input == 11) {
//			System.out.println("Hai selezionato: VisualizzaContest");
//			return new Request(this,Action.GetContests);
//		} else if (input== 12) {
//			System.out.println("Hai selezionato: Cambia Ruolo");
//			return new Request(this, Action.DefineRole);
//		}
//			System.out.println("Nessuna Scelta");
//			return null;
//		}

	

//	public void getNotifications() {
//		if(notifications.isEmpty()) System.out.println("Non ci sono nitifiche per te al momento!");
//		else {
//			Scanner scanner = new Scanner(System.in);
//			System.out.println("Ci sono delle notifiche per te! 1- per vedere la prossima notifica, 2- per uscire");
//			int select = scanner.nextInt();
//			while(!notifications.isEmpty() && select==1) {
//			Notification noty = notifications.poll();
//			System.out.println(noty.getMessage());
//			System.out.println("1- per vedere la prossima notifica, 2- per uscire");
//			select = scanner.nextInt();
//			}
//		}
//	}
	

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

	public Queue<Notification> getNotifications() {
		return notifications;
	}

	public List<Element> getSavedElements() {
		return savedElements;
	}
	
	@Override
	public Role getRole() {
	return role;
}

	public void setRole(Role role) {
		this.role = role;
	}
}
		
	