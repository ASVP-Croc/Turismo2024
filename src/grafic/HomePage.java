package grafic;

import java.io.BufferedReader;
import java.io.IOException;

import managers.ViewManager;

public class HomePage implements WebPage {

	@Override
	public void open() {

		System.out.println("Benvenuto Turista!");

		boolean chosen = false;

		while (!chosen) {
			System.out.println(
					"1 - Mostra POI\n2 - Mostra Tour\n3 - Mostra Contest\n4 - Login\n5 - Registrazione\n0 - Close");
			try {
				int result = Integer.parseInt(ViewManager.getReader().readLine());
				switch (result) {
				case 0 -> {
					ViewManager.stop();
					chosen = true;
				}
				case 1 -> {
					ViewManager.changePage(new ShowPOI());
					chosen = true;
				}
				case 2 -> {
					ViewManager.changePage(new ShowTour());
					chosen = true;
				}
				case 3 -> {
					ViewManager.changePage(new ShowContest());
					chosen = true;
				}
				case 4 -> {
					ViewManager.changePage(new Login());
					chosen = true;
				}
				case 5 -> {
					ViewManager.changePage(new Registration());
					chosen = true;
				}
				default -> System.out.println("Scelta non valida: " + result);
				}

			} catch (NumberFormatException | IOException e) {
				System.out.println("Scelta non valida");
			}
		}
	}
}
