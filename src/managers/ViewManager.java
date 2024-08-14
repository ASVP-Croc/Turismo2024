package managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import grafic.HomePage;
import grafic.WebPage;

public class ViewManager {
	
	private static BufferedReader reader;
	private static WebPage nextPage;
	private static boolean run = true;
	
	public static void start() throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		nextPage = new HomePage();
		while (run) {
			nextPage.open();
		}
		reader.close();
	}
	
	public static void stop() {
		run = false;
	}
	
	public static void changePage(WebPage page) {
		nextPage = page;
	}

	public static BufferedReader getReader() {
		return reader;
	}
}
