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
			return toCheck.get();
		} else 
			throw new SQLIntegrityConstraintViolationException("L'elemento non è stato trovato");
	}
	
	public List<T> findAll(){
		return elementRepository.findAll();
	}

	public List<T> findByValidated(ElementStatus elementStatus) throws SQLIntegrityConstraintViolationException {
		Optional<List<T>> toCheck = elementRepository.findByValidated(elementStatus);
		
		if (toCheck.isPresent()) {
			return toCheck.get();
		} else 
			throw new SQLIntegrityConstraintViolationException("L'oggetto/gli oggetti non sono stati trovati");
		
	}
	
	public T add(T elementToAdd) {
		try {
			findById(elementToAdd.getId());
			System.out.println("L'elemento " + elementToAdd.getName() + " è stato trovato e aggiornato");
		} catch (SQLIntegrityConstraintViolationException e) {
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

	public T updateStatus(int id, ElementStatus elementStatus) throws ElementNotFoundException {
		T element = elementRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Elemento non trovato"));
		element.setValidated(elementStatus);
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

	public Element checkStatusElement(int id, ElementStatus status) {
		try {
			Element toReturn = findById(id);
			if (toReturn.getValidated() == status) {
				return toReturn;
			}			
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
