package com.speriamochemelacavo.turismo2024.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.repository.ElementRepository;

@Service
public abstract class ElementsService<T extends Element> {
	
	@Autowired
	private ElementRepository<T> repository;
	
	@Autowired
	private ValidationsService<T> validationService;
	
	private static boolean isLoaded;
	
	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		ElementsService.isLoaded = isLoaded;
	}

	public T findById(int elemToFindId) {
		return repository.findById(elemToFindId).orElseThrow();
	}
	
	public List<T> findAll(){
		return repository.findAll();
	}
	
	public void add(T elementToAdd, User author) {
		elementToAdd.setAuthor(author);
		repository.save(elementToAdd);
		if (validationService.requestValidation(elementToAdd)) {
			update(elementToAdd);
			}
	}
	
	public void addAll(List<T> elementsToAdd, User author) {
		elementsToAdd.stream().forEach(elem-> add(elem, author));
	}
	
	public void update(T elementToUpdate) {
		if (validationService.requestValidation(elementToUpdate)) {
			repository.save(elementToUpdate);
			}
	}
	
	public void delete(T elementToDelete) {
		repository.delete(elementToDelete);
	}
	
	public void deleteAll(List<T> elementsToDelete) {
		repository.deleteAll(elementsToDelete);
	}
}
