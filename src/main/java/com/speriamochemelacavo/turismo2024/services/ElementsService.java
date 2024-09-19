package com.speriamochemelacavo.turismo2024.services;

import java.util.List;
import java.util.Optional;

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

	public T findById(int elemToFindId) {
		return elementRepository.findById(elemToFindId).orElseThrow();
	}
	
	public List<T> findAll(){
		return elementRepository.findAll();
	}
	
	public void add(T elementToAdd) {
		//il nuovo elemento ha id?
		//altrimenti devo controllare se esiste un elemento uguale con altri parametri
		//es. nome, autore o altro
//		T optionalElement;
//		try {
//			optionalElement = findById(elementToAdd.getId());
//			elementToAdd.setId(optionalElement.getId());
//		} catch (Exception e) {
//			
//		}
		if(!elementRepository.findAll().contains(elementToAdd)){
			 elementRepository.save(elementToAdd);
		     validationService.requestValidation(elementToAdd);
		}
	}
	
	//i tag appartengono anche ai Content o solo agli ElementWithContent o solo ai POI?
	public void add(T elementToAdd, List<Tag> tags) {
		elementToAdd.getTags().addAll(tags);
		add(elementToAdd);
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
	
	public void updateElement(T element) {
		Optional<T> optionalElement = elementRepository.findById(element.getId());
		if(optionalElement.isPresent()) {
			 T findElement  = optionalElement.get();
			    findElement.setName(element.getName());
			    findElement.setDescription(element.getDescription());
			    findElement.setValidation(false);
			    elementRepository.save(findElement);
		}
	}

}
