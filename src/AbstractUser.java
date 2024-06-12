
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUser {
	private final int id;
	private final String name;
	private final String surname;
	private String userName;
	private final String email;
	private final String phoneNumber;

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

}