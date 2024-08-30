package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.Notification;
import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;
import com.speriamochemelacavo.turismo2024.repository.UserRepository;

@Service
public class AccountsService {
	
	@Autowired
	private AuthenticatedUser loggedUser;
	
	@Autowired
	UserRepository userRepository;
	
	public void saveLoggedUser(int id) {
		this.loggedUser = findById(id);
	}
	
	public AuthenticatedUser getLoggedUser() {
		return this.loggedUser;
	}
	
	public List<AuthenticatedUser> findAll() {
		return userRepository.findAll();
	}
	
	public AuthenticatedUser findById(int userToFindId) {
		return userRepository.findById(userToFindId).orElseThrow();
	}

	public void addUser(AuthenticatedUser userToAdd) {
		userRepository.save(userToAdd);
	}
	
	public void addUsers(List<AuthenticatedUser> usersToAdd) {
		userRepository.saveAll(usersToAdd);
	}

	public void updateUser(AuthenticatedUser useruserToUptade) {
		userRepository.save(useruserToUptade);
	}
	
	public void deleteUserById(int userToDeleteId) {
		userRepository.deleteById(userToDeleteId);
	}
	
	public List<Element> getSavedElements(int userToGetSavedElementsId){
		return findById(userToGetSavedElementsId).getSavedElements();
	}

	public List<Notification> getNotifications(int userToGetNotificationId){
		return findById(userToGetNotificationId).getNotifications();
	}
	
	public String userToString(AuthenticatedUser userToString) {
        return "Utente: "
        		+ userToString.getId()
        		+ userToString.getName() 
        		+ userToString.getSurname()
        		+ userToString.getUserName()
        		+ userToString.getEmail()
        		+ userToString.getPhoneNumber()
        		+ userToString.getRole();
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
