package grafic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import users.AbstractAuthenticatedUser;

public class MainPage {
	public static void startMainPage(AbstractAuthenticatedUser userLogged) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Benvenuto Turista!");

		boolean chosen = false;

		while (!chosen) {
			try {
				System.out.println("Seleziona il tuo account, oppure < 0 > per uscire:\n");
				List<AbstractAuthenticatedUser> users = AbstractAuthenticatedUser.getUsers();
				for (int i = 0; i < users.size(); i++) {
					AbstractAuthenticatedUser tmp = AbstractAuthenticatedUser.getUsers().get(i);
					System.out.println(tmp.getId() + ": " + tmp.getSurname() + ", " + tmp.getName() + " --> "
							+ tmp.getUserName() + "(" + tmp.getRole() + ")");
				}

				System.out.print("\n");
				int result = Integer.parseInt(reader.readLine()) - 1;

				if (result == -1) {
					System.out.println("\nDisconnessione effettuata. A Presto!");
					chosen = true;
				} else if (result > -1 && result <= users.size()) {
					userLogged = users.get(result);
					System.out.println("\nHai eseguito l'accesso come - " + userLogged.getUserName() + " -");
					chosen = true;
				} else if (result < -1 || result > users.size() - 1) {
					System.out.println("\nUtente non trovato");
				}
			} catch (NumberFormatException e) {
				System.out.println("Scelta non valida");
			} catch (IOException e) {
				System.out.println("Scelta non valida");
			}
		}
	}
}
