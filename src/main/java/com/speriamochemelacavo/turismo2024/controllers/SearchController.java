package com.speriamochemelacavo.turismo2024.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.services.ElementsService;

@RestController("/search")
public class SearchController {
	
	@Autowired
	private ElementsService<Element> elementService;

	@PostMapping("/site")
	public List<Element> searchElementsSite(Model model){
		return elementService.findAll();
	}
}
