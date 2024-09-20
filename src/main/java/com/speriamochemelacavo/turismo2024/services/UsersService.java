package com.speriamochemelacavo.turismo2024.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.repository.UserRepository;

@Service
public class UsersService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(int userToFindId) {
		return userRepository.findById(userToFindId).orElseThrow();
	}
	
	public User findByUserName(String userToFindUserName) {
		return userRepository.findByUsername(userToFindUserName);
	}
	
	public List<User> findByRole(Role userToFindRole) {
		return userRepository.findByRole(userToFindRole);
	}

	public void addUser(User userToAdd) {
        userRepository.save(userToAdd);
	}
	
	public void deleteUserById(int userToDeleteId) {
		userRepository.deleteById(userToDeleteId);
	}
//	
//	public <T extends Element> void addNewSavedElement(T elementToAdd, int userToSaveElementId) {
//		User userToUpdate = findById(userToSaveElementId);
//		userToUpdate.getSavedElements().add(elementToAdd);
//		updateUser(userToUpdate);
//	}
}
