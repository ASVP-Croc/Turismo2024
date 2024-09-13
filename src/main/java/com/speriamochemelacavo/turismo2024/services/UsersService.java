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

	public void updateUser(User userToUpdate) {
		userRepository.save(userToUpdate);
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
}
