package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.repository.UserRepository;

@Service
public class UsersService {

	private boolean isLoaded = false;
	
	@Autowired
	private UserRepository userRepository;

	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(int userToFindId) {
		return userRepository.findById(userToFindId).orElseThrow();
	}
	
	public User findByUserName(String userToFindUserName) {
		return userRepository.findByUserName(userToFindUserName);
	}
	
	public List<User> findByRole(Role userToFindRole) {
		return userRepository.findByRole(userToFindRole);
	}

	public void addUser(User userToAdd) {
		userRepository.save(userToAdd);
	}

	public void updateUser(User useruserToUptade) {
		userRepository.save(useruserToUptade);
	}
	
	public void deleteUserById(int userToDeleteId) {
		userRepository.deleteById(userToDeleteId);
	}
	
	public void addNewSavedElement(Element elementToAdd, int userToSaveElementId) {
		User userToUpdate = findById(userToSaveElementId);
		userToUpdate.getSavedElements().add(elementToAdd);
		updateUser(userToUpdate);
		}
	
	public String userToString(User userToString) {
        return "Utente: "
        		+ userToString.getId() + "\n"
        		+ userToString.getName()  + "\n"
        		+ userToString.getSurname() + "\n"
        		+ userToString.getUserName() + "\n"
        		+ userToString.getEmail() + "\n"
        		+ userToString.getPhoneNumber() + "\n"
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
