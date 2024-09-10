package com.speriamochemelacavo.turismo2024.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.repository.ElementRepository;

@Service
public abstract class ElementsService<T extends Element>{
	
	@Autowired
	private ElementRepository<T> repository;
	
	@Autowired
	private TagsService tagService;
	
	@Autowired
	private AccountsService accountService;
	
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
	
	public List<T> findByTags(List<String> tagsToFind) {
		List<Tag> toFind = new ArrayList<Tag>();
		return repository.findAllElementByTags(toFind);
	}
	
	public List<T> findAll(){
		return repository.findAll();
	}
	
	public void addElement(T element) {
		element.setAuthor(accountService.findById(accountService.getLoggedUser()));
		element.setCity(accountService.findById(accountService.getLoggedUser()).getComune());
		repository.save(element);
		if (validationService.requestValidation(element)) {
			repository.save(element);			
		}
	}
	
	public void addElements(List<T> elements) {
		elements.stream().forEach(elem-> addElement(elem));
	}
	
	public void updateElement(T element) {
		validationService.requestValidation(element);
		repository.save(element);
	}
	
	public void deleteElement(T element) {
		repository.delete(element);
	}
}
