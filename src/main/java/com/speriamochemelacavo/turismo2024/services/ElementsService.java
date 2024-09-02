package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.repository.ElementRepository;

@Service
public abstract class ElementsService<T extends Element>{
	
	@Autowired
	private ElementRepository<T> repository;
	
	@Autowired
	private AccountsService accountService;
	
	@Autowired
	private ReportsService reportService;
	
	public T findById(int elemToFindId) {
		return repository.findById(elemToFindId).orElseThrow();
	}
	
	public List<T> findAll(){
		return repository.findAll();
	}
	
	public void addElement(T element) {
		element.setAuthor(accountService.getLoggedUser());
		element.setCAP(accountService.getLoggedUser().getCAP());
		repository.save(element);
	}
	
	public void addElements(List<T> elemsToAdd) {
		elemsToAdd.stream().forEach(e -> {
			e.setAuthor(accountService.getLoggedUser());
			e.setCAP(accountService.getLoggedUser().getCAP());
			});
		repository.saveAll(elemsToAdd);
	}
	
	public void updateElement(T element) {
		repository.save(element);
	}
	
	public void deleteElement(T element) {
		repository.delete(element);
	}
}
