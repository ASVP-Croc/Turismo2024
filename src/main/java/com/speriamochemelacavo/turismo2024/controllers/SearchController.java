package com.speriamochemelacavo.turismo2024.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.services.ElementsService;

@RestController
public class SearchController {
	
	@Autowired
	private ElementsService<Element> elementService;

	@PostMapping("/search/site")
	public RedirectView searchElementsSite(Model model, @RequestParam Tag tag){
		List<Tag> toFind = new ArrayList<Tag>();
		toFind.add(tag);
		model.addAttribute("listElements", elementService.findByTags(toFind));
		return new RedirectView("/elements");
	}
}
