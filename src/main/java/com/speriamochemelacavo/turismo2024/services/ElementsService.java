package com.speriamochemelacavo.turismo2024.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.repository.ElementRepository;

@Service
public class ElementsService<T extends Element> {
	
	@Autowired
	protected ElementRepository<T> elementRepository;
	
	@Autowired
	private ValidationsService<T> validationService;

	public T findById(int elemToFindId) throws SQLIntegrityConstraintViolationException {
		Optional<T> toCheck = elementRepository.findById(elemToFindId);
		if (toCheck.isPresent()) {
//	    	TODO togliere prima della produzione
			System.out.println("L'elemento " + toCheck.get().getName() + " è stato trovato");
			return toCheck.get();
		} else 
			throw new SQLIntegrityConstraintViolationException("L'elemento con ID " + elemToFindId + " non è stato trovato");
	}
	
	public List<T> findAll(){
		return elementRepository.findAll();
	}
	
	public void add(T elementToAdd) {
		try {
			findById(elementToAdd.getId());
			System.out.println("L'elemento " + elementToAdd.getName() + " è stato aggiornato");
		} catch (SQLIntegrityConstraintViolationException e) {
//	    	TODO togliere prima della produzione
			System.out.println(elementToAdd.getName() + " - " + e.getLocalizedMessage() + ", quindi è stato aggiunto");
		}
		elementRepository.save(elementToAdd);
		validationService.requestValidation(elementToAdd);
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
