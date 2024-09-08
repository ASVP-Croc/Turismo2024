package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.repository.ElementRepository;

@Service
public abstract class ElementsService<T extends Element>{
	
	@Autowired
	private ElementRepository<T> repository;
	
	@Autowired
	private AccountsService accountService;
	
	@Autowired
	private ValidationsService<T> validationService;
	
	private boolean isLoaded;
	
	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	
	public T findById(int elemToFindId) {
		return repository.findById(elemToFindId).orElseThrow();
	}
	
	public List<T> findAll(){
		return repository.findAll();
	}
	
	public void addElement(T element) {
		element.setAuthor(accountService.findById(accountService.getLoggedUser()));
		element.setCAP(accountService.findById(accountService.getLoggedUser()).getCAP());
		repository.save(element);
		if (validationService.sendValidation(element)) {
			repository.save(element);			
		}
	}
	
	public void addElements(List<T> elemsToAdd) {
		elemsToAdd.stream().forEach(e -> addElement(e));
	}
	
	public void updateElement(T element) {
		validationService.sendValidation(element);
		repository.save(element);
	}
	
	public void deleteElement(T element) {
		repository.delete(element);
	}
}
