package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;
import com.speriamochemelacavo.turismo2024.repository.ElementRepository;

@Service
public class ElementsService<T extends Element>{
	
	@Autowired
	ElementRepository<T> elementRepository;

	@Autowired
	AccountsService accountService;
	
	public T findById(int elemToFindId) {
		return elementRepository.findById(elemToFindId).orElseThrow();
	}
	
	public List<T> findAll(){
		return elementRepository.findAll();
	}
	
	public void addElement(T element, AuthenticatedUser author) {
		element.setAuthor(author);
		elementRepository.save(element);
	}
	
	public void addElements(List<T> elemsToAdd) {
		elementRepository.saveAll(elemsToAdd);
	}

}
