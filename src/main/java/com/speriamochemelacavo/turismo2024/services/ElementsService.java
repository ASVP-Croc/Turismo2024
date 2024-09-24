package com.speriamochemelacavo.turismo2024.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.speriamochemelacavo.turismo2024.exception.ElementNotFoundException;
import com.speriamochemelacavo.turismo2024.models.elements.ElementStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.repository.ElementRepository;

@Service
public class ElementsService<T extends Element> {
	
	@Autowired
	protected ElementRepository<T> elementRepository;

	public ElementsService() {
		
	}
	
	public T findById(int elemToFindId) throws SQLIntegrityConstraintViolationException {
		Optional<T> toCheck = elementRepository.findById(elemToFindId);
		
		if (toCheck.isPresent()) {
//	    	TODO togliere prima della produzione
			return toCheck.get();
		} else 
			throw new SQLIntegrityConstraintViolationException("L'elemento non è stato trovato");
	}
	
	public List<T> findAll(){
		return elementRepository.findAll();
	}

	public List<T> findByValidated(ElementStatus elementStatus) {
		return elementRepository.findAllByValidated(elementStatus);
	}
	
	public T add(T elementToAdd) {
		try {
			findById(elementToAdd.getId());
			elementToAdd.setValidation(ElementStatus.PENDING);
			System.out.println("L'elemento " + elementToAdd.getName() + " è stato trovato e aggiornato");
		} catch (SQLIntegrityConstraintViolationException e) {
//	    	TODO togliere prima della produzione
			System.out.println(elementToAdd.getName() + " - " + e.getLocalizedMessage() + ", quindi è stato aggiunto");
		}
		
		T toReturn = elementRepository.save(elementToAdd);
		return toReturn;
	}
	
	public List<T> addAll(List<T> elementsToAdd) {
		List<T> toReturn = new ArrayList<>();
		elementsToAdd.stream().forEach(p -> toReturn.add(add(p)));
		return toReturn;
	}
	
	public T update(T elementToAdd) {
		try {
			findById(elementToAdd.getId());
//	    	TODO togliere prima della produzione

			System.out.println("L'elemento " + elementToAdd.getName() + " è stato trovato e aggiornato");
		} catch (SQLIntegrityConstraintViolationException e) {
//	    	TODO togliere prima della produzione
			System.out.println(elementToAdd.getName() + " - " + e.getLocalizedMessage() + ", quindi è stato aggiunto");
		}

		return elementRepository.save(elementToAdd);
    }

	public T updateStatus(int id, ElementStatus elementStatus) throws ElementNotFoundException {
		T element = elementRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Elemento non trovato"));
		element.setValidation(elementStatus);
		return elementRepository.save(element);
	}

	public void deleteById(Integer id) {
		Optional<T> element = elementRepository.findById(id);
		element.ifPresent(e -> {
			if(e.getValidated() == ElementStatus.REJECTED) {
				delete(e);
			}
		});
	}
	public void delete(T elementToDelete) {
		elementRepository.delete(elementToDelete);
	}
	
	public void deleteAll(List<T> elementsToDelete) {
		elementRepository.deleteAll(elementsToDelete);
	}

}
