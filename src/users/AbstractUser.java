package users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import elements.Request;

public abstract class AbstractUser {
	private final int id;
	private final String name;
	private final String surname;
	private String userName;
	private String email;
	private String phoneNumber;
	private Role role = Role.Tourist;
	private List<Action> actions;

	protected AbstractUser(int id, String name, String surname, String userName, String email, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.actions = new ArrayList<>();
	}
	
	public Request sendRequest() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("1: CreaPOI " + "-" + " 2: CreaItinerario " + "-" + " 3: CreaContenuto per Itinerario " + "-"
				+ " 4: CreaContenuto per POI");
		int input = Integer.parseInt(reader.readLine());
		if (input == 1) {
			System.out.println("Hai selezionato: CreaPOI");
			return new Request(this,Action.CreatePOI);
		} else if (input == 2) {
			System.out.println("Hai selezionato: CreaItinerario");
			return new Request(this,Action.CreateTour);
		} else if (input == 3) {
			System.out.println("Hai selezionato: CreaContenuto per Itinerario");
			return new Request(this,Action.CreateContentTour);
		} else if (input == 4) {
			System.out.println("Hai selezionato: CreaContenuto per POI");
			return new Request(this,Action.CreateContentPOI);
		} else {
			System.out.println("Nessuna Scelta");
			return null;
		}
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

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}