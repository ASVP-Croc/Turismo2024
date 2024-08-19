package managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import elements.Element;
import elements.Notification;
import elements.Request;
import users.*;

public class AccountsManager {
	private static final Map<Integer, AbstractAuthenticatedUser> users = new HashMap<>();

	public static boolean execute(Request request) {
		Action action = request.getAction();
		if(action==Action.DefineRole) {
			return setRole();
		} else if(action==Action.Registration) {
			return addUser(request.getUser());}
		return false;
	}
	
	public static boolean execute(Request request, Element element) {
		if(request.getAction()==Action.SaveElement) {
			return saveElement(request, element);
			}
		return false;
	}
	
	private static boolean saveElement(Request request, Element element) {
		return users.get(request.getUser().getId()).addElement(element);
	}

	private static boolean setRole() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un utente tramite l'Id per cambiare il ruolo.");
		getUsers().forEach(user->System.out.println("Username: "+user.getUserName()+ "Id: "+ user.getId()+ "Ruolo: "+user.getRole()));
		Integer id = scanner.nextInt();
		System.out.println("Seleziona un nuovo ruolo per l'utente selezionato! 1- per Contributor, 2- per Contributor Autorizzato,"
				+ "3- per Curatore, 4- per Animatore");
		int select = scanner.nextInt();
		Role role = Role.AuthenticatedTourist;
		if(select==1) role = Role.Contributor;
		else if(select==2) role = Role.AuthorizedContributor;
		else if(select==3) role = Role.Curator;
		else if(select==4) role = Role.Animator;
		return users.get(id).setRole(role);
	}
	
	private static Stream<AbstractAuthenticatedUser> getUsers(){
		return users.values().stream();
	}
	
	private static boolean addUser(GeneralUser user) {
		if(user.getRole()==Role.Tourist) {
			System.out.println("Inserisci i tuo i dati per completare l'operazione");
			Scanner scanner = new Scanner(System.in);
			System.out.println("Nome: ");
			String name = scanner.nextLine();
			System.out.println("Cognome: ");
			String surname = scanner.nextLine();
			System.out.println("Username: ");
			String username = scanner.nextLine();
			System.out.println("E-mail: ");
			String email = scanner.nextLine();
			System.out.println("Numero Telefonico: ");
			String phoneNumber = scanner.nextLine();
			AuthenticatedUser newUser = new AuthenticatedUser(name, surname, username, email, phoneNumber);
			users.put(newUser.getId(), newUser);
			return true;
		}
		return false;
	}
	
	public static boolean execute(Request request, Notification notify, Integer id) {
		return users.get(id).addNotification(notify);
	}

	public static boolean execute(Request request, Notification notification) {
		Action action = request.getAction();
		if(action==Action.CreateContentInContest) {
			getUsers().filter(user->user.getRole()==Role.Animator).forEach(user->user.addNotification(notification));
			return true;
		} else if(action==Action.AddPOIInTour || action==Action.CreateContentInPOI || action==Action.CreateContentInTour || action==Action.CreatePOI
				|| action==Action.CreateTour) {
			getUsers().filter(user->user.getRole()==Role.Curator).forEach(user->user.addNotification(notification));
			return true;
		}
		return false;
	}
	
	


}
