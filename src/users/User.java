package users;

import java.util.Scanner;

import elements.Request;

public class User implements GeneralUser {

	public Request sendRequest() {
		Scanner scanner = new Scanner(System.in);
		System.out.println(" 1: Visualizza POIs "+"-"+"2: Visualizza Tour "+"-"+"3: Visualizza Contest"+"-"
		+"4: Registrazione"+"-"+"5: Login");
		Integer input = scanner.nextInt();
		if (input == 1) {
			System.out.println("Hai selezionato: VisualizzaPOI");
			return new Request(this,Action.GetPOIs);
		} else if (input == 2) {
			System.out.println("Hai selezionato: VisualizzaItinerario");
			return new Request(this,Action.GetTours);
		} else if (input == 3) {
			System.out.println("Hai selezionato: VisualizzaContest");
			return new Request(this,Action.GetContests);
		} else if (input==4) {
			System.out.println("Hai selezionato: Registrazione");
			return new Request(this, Action.Registration);
		} else if (input==5) {
			System.out.println("Hai selezionato: Login");
			return new Request(this, Action.Login);
			}
		else System.out.println("Nessuna Scelta");
			return null;
		}


	@Override
	public Integer getId() {
		return 0;
	}

	@Override
	public Role getRole() {
		return Role.Tourist;
	}
		
}
