package com.speriamochemelacavo.turismo2024.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import com.speriamochemelacavo.turismo2024.elements.Element;
import com.speriamochemelacavo.turismo2024.elements.Notification;
import com.speriamochemelacavo.turismo2024.users.*;

public class AccountsManager {
	
	private static final List<AuthenticatedUser> users = new ArrayList<>();
	
	public List<AuthenticatedUser> getUsers(){
		return users;
	}
	
	public Stream<AuthenticatedUser> getStreamUser(){
		return users.stream();
	}
	
	public AuthenticatedUser findById(int id) {
		return users.stream().filter(user->user.getId()==id).findFirst().get();
	}
	
//	public static boolean execute(Request request) {
//		Action action = request.getAction();
//		if(action==Action.DefineRole) {
//			return setRole();
//		} else if(action==Action.Registration) {
//			return addUser(request.getUser());
//		} else if(action==Action.Login) {
//			login(request.getUser());
//		}
//		return false;
//	}
	
//	public static boolean execute(Request request, Element element) {
//		if(request.getAction()==Action.SaveElement) {
//			return saveElement(request, element);
//			}
//		return false;
//	}
//	
//	private static boolean login(GeneralUser user) {
//		Scanner scanner = new Scanner(System.in);
//		users.values().forEach(utente->System.out.println("ID: "+utente.getId()+" Username: "+ utente.getUserName()));
//		System.out.println("Inserisci l'id per selezionare il tuo account: ");
//		Integer id = scanner.nextInt();
//		user = users.get(id);
//		return true;
//	}
	
	private static boolean saveElement(AuthenticatedUser user, Element element) {
		return user.getSavedElements().add(element);
	}

//	private static boolean setRole() {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Seleziona un utente tramite l'Id per cambiare il ruolo.");
//		getUsers().forEach(user->System.out.println("Username: "+user.getUserName()+ "Id: "+ user.getId()+ "Ruolo: "+user.getRole()));
//		Integer id = scanner.nextInt();
//		System.out.println("Seleziona un nuovo ruolo per l'utente selezionato! 1- per Contributor, 2- per Contributor Autorizzato,"
//				+ "3- per Curatore, 4- per Animatore");
//		int select = scanner.nextInt();
//		Role role = Role.AuthenticatedTourist;
//		if(select==1) role = Role.Contributor;
//		else if(select==2) role = Role.AuthorizedContributor;
//		else if(select==3) role = Role.Curator;
//		else if(select==4) role = Role.Animator;
//		return users.get(id).setRole(role);
//	}
	
	private static boolean addUser(GeneralUser user) {
		Scanner scanner = new Scanner(System.in);
		if(user.getRole()==Role.Tourist) {
			System.out.println("Inserisci il tuo nome: ");
			String name = scanner.nextLine();
			System.out.println("Inserisci il tuo cognome: ");
			String surname = scanner.nextLine();
			System.out.println("Inserisci il tuo username: ");
			String username = scanner.nextLine();
			System.out.println("Inserisci il tuo email: ");
			String email = scanner.nextLine();
			System.out.println("Inserisci il tuo numero: ");
			String number = scanner.nextLine();
			AuthenticatedUser newUser = new AuthenticatedUser(name, surname, username, email, number, Role.AuthenticatedTourist);
			users.add(newUser);
			return true;
		} 
		return false;
	}
	
	public static boolean addNotification(AuthenticatedUser user, Notification notify) {
		return user.getNotifications().add(notify);
	}

//	public static boolean execute(Request request, Notification notification) {
//		Action action = request.getAction();
//		if(action==Action.CreateContentInContest) {
//			getUsers().filter(user->user.getRole()==Role.Animator).forEach(user->addNotification(user, notification));
//		} else if(action==Action.AddPOIInTour || action==Action.CreateContentInPOI || action==Action.CreateContentInTour || action==Action.CreatePOI
//				|| action==Action.CreateTour) {
//			getUsers().filter(user->user.getRole()==Role.Curator).forEach(user->user.addNotification(notification));
//			return true;
//		}
//		return false;
//	}
	
	


}
