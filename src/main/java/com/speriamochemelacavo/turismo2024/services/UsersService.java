package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.repository.UserRepository;
import com.speriamochemelacavo.turismo2024.security.UserPrinciple;

@Service
public class UsersService implements UserDetailsService{

	@Autowired
	private UserPrinciple userPrinciple;
	
	//TODO da togliere - serve solamente per far vedere il bottone all'interno del div menù
	private boolean isLoaded = false;
	
	@Autowired
	private UserRepository userRepository;

	//TODO da togliere - serve solamente per far vedere il bottone all'interno del div menù
	public boolean isLoaded() {
		return isLoaded;
	}
	
	//TODO da togliere - serve solamente per far vedere il bottone all'interno del div menù
	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}
	
	public boolean isLogged() {
		return userPrinciple.getRole() != Role.Tourist;
	}
	
	public String getUsername() {
		return userPrinciple.getUsername();
	}
	
	public String getCAP() {
		return userPrinciple.getCAP();
	}

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUserName(username);
		userPrinciple.setUser(user);
		if (user == null) {
			throw new UsernameNotFoundException("utente non trovato");
		}
		return userPrinciple;
	}
}
