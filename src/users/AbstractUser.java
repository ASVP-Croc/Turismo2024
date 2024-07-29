package users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	private List<AbstractUser> users;

	protected AbstractUser(int id, String name, String surname, String userName, String email, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.users = new ArrayList<>();
	}

	public Request sendRequest() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1: CreaPOI " + "-" + " 2: CreaItinerario " + "-" + " 3: CreaContenuto per Itinerario " + "-"
				+ " 4: CreaContenuto per POI "+"-"+"5: Aggiungi POI a Itinerario "+"-"+"6: CreaContest "+"-"+"7: Crea Contenuto per Contest "+
				"-"+"8: Validare Elementi o Contenuti "+"-"+" 9: Visualizza POIs "+"-"+"10: Visualizza Tour "+"-"+"11: Visualizza Contest");
		Integer input = scanner.nextInt();
		if (input == 1) {
			System.out.println("Hai selezionato: CreaPOI");
			return new Request(this,Action.CreatePOI);
		} else if (input == 2) {
			System.out.println("Hai selezionato: CreaItinerario");
			return new Request(this,Action.CreateTour);
		} else if (input == 3) {
			System.out.println("Hai selezionato: CreaContenuto per Itinerario");
			return new Request(this,Action.CreateContentInTour);
		} else if (input == 4) {
			System.out.println("Hai selezionato: CreaContenuto per POI");
			return new Request(this,Action.CreateContentInPOI);
		} else if (input == 5) {
			System.out.println("Hai selezionato: Aggiungi POI a Itinerario");
			return new Request(this,Action.AddPOIInTour);
		} else if (input == 6) {
			System.out.println("Hai selezionato: CreaContest");
			return new Request(this,Action.CreateContest);
		} else if (input == 7) {
			System.out.println("Hai selezionato: CreaContenuto per Contest");
			return new Request(this,Action.CreateContentInContest);
		} else if (input == 8) {
			System.out.println("Hai selezionato: Validare elementi o contenuti");
			return new Request(this,Action.Validate);
		} else if (input == 9) {
			System.out.println("Hai selezionato: VisualizzaPOI");
			return new Request(this,Action.GetPOIs);
		} else if (input == 10) {
			System.out.println("Hai selezionato: VisualizzaItinerario");
			return new Request(this,Action.GetTours);
		} else if (input == 11) {
			System.out.println("Hai selezionato: VisualizzaContest");
			return new Request(this,Action.GetContests);
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

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
}