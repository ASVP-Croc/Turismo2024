package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.repository.ElementRepository;

@Service
public class ElementsService<T extends Element> {
	
	@Autowired
	private ElementRepository<T> elementRepository;
	
	@Autowired
	private ValidationsService<T> validationService;

	public T findById(int elemToFindId) {
		return elementRepository.findById(elemToFindId).orElseThrow();
	}
	
	public List<T> findAll(){
		return elementRepository.findAll();
	}
	
	public void add(T elementToAdd, User author) {
		T optionalElement;
		try {
			optionalElement = findById(elementToAdd.getId());
			elementToAdd.setId(optionalElement.getId());
		} catch (Exception e) {
			elementToAdd.setAuthor(author);
		}
        elementRepository.save(elementToAdd);
        validationService.requestValidation(elementToAdd);
	}
	
	public void add(T elementToAdd, User author, List<Tag> tags) {
		elementToAdd.getTags().addAll(tags);
		add(elementToAdd, author);
	}

	public void deleteById(Integer id) {
		elementRepository.deleteById(id);
	}
	public void delete(T elementToDelete) {
		elementRepository.delete(elementToDelete);
	}
	
	public void deleteAll(List<T> elementsToDelete) {
		elementRepository.deleteAll(elementsToDelete);
	}

}
