package com.speriamochemelacavo.turismo2024.grafic;

import java.io.BufferedReader;
import java.io.IOException;

import com.speriamochemelacavo.turismo2024.managers.ViewManager;

public class Login implements WebPage {
	
	public void open() {

		boolean chosen = false;
		String username = "";
		String password = "";
		
		while (!chosen) {
			System.out.println(
					"Inserisci le tue credenziali (Matteo - 12345678!\n");
			try {
				System.out.println(
						"Username:");
				username = ViewManager.getReader().readLine();
				System.out.println(
						"Password:");
				password = ViewManager.getReader().readLine();
			} catch (IOException e) {
				System.out.println("Scelta non valida");
			}
			if (username.equals("Matteo") && password.equals("12345678!")) {
				ViewManager.changePage(new MainPage());
				chosen = true;
				}
			else {
				System.out.println("Username o Password errati!");
			}
				
		}
	}
}
