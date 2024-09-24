package com.speriamochemelacavo.turismo2024.services;

import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.speriamochemelacavo.turismo2024.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.repository.UserRepository;
import org.springframework.web.client.ResourceAccessException;

@Service
public class UsersService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(int userToFindId) throws SQLIntegrityConstraintViolationException {
		Optional<User> toCheck = userRepository.findById(userToFindId);
		
		if (toCheck.isPresent()) {
			return toCheck.get();
		} else 
			throw new SQLIntegrityConstraintViolationException("L'Utente non è stato trovato");
	}
	
	public User findByUserName(String userToFindUserName) throws SQLIntegrityConstraintViolationException {
		Optional<User> toCheck = userRepository.findByUsername(userToFindUserName);
		
		if (toCheck.isPresent()) {
			return toCheck.get();
		} else 
			throw new SQLIntegrityConstraintViolationException("L'Utente non è stato trovato");
	}
	
	public List<User> findByRole(Role userToFindRole) throws SQLIntegrityConstraintViolationException {
		Optional<List<User>> toCheck = userRepository.findByRole(userToFindRole);
		
		if (toCheck.isPresent()) {
			return toCheck.get();
		} else 
			throw new SQLIntegrityConstraintViolationException("L'Utente/gli utenti non sono stati trovati");
	}

	public void add(User userToAdd) {
        userRepository.save(userToAdd);
	}
	
	public void addAll(List<User> userToAdd) {
        userRepository.saveAll(userToAdd);
	}

	public User update(int id, User userToUpdate) throws UserNotFoundException {
		//TODO
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non trovato!"));
		user.setName(userToUpdate.getName());
		user.setSurname(userToUpdate.getSurname());
		user.setUsername(userToUpdate.getUsername());
		user.setEmail(userToUpdate.getEmail());
		user.setPassword(userToUpdate.getPassword());
		user.setPhoneNumber(userToUpdate.getPhoneNumber());
		user.setAddress(userToUpdate.getAddress());
		user.setCity(userToUpdate.getCity());
		user.setCAP(userToUpdate.getCAP());
		return userRepository.save(user);
	}

	public User updateUserRole(int id, User admin, Role newRole) throws AccessDeniedException, UserNotFoundException {
		if(!admin.getRole().equals(Role.ROLE_ADMINISTRATOR)) {
			throw new AccessDeniedException(
					"Solo gli amministratori possono aggiornare i ruoli!"
			);
		}
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non trovato!"));
		user.setRole(newRole);
		return userRepository.save(user);
	}
	
	public void delete(int userToDeleteId) {
		userRepository.deleteById(userToDeleteId);
	}
//	
//	public <T extends Element> void addNewSavedElement(T elementToAdd, int userToSaveElementId) {
//		User userToUpdate = findById(userToSaveElementId);
//		userToUpdate.getSavedElements().add(elementToAdd);
//		updateUser(userToUpdate);
//	}
}
