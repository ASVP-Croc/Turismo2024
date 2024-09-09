package com.speriamochemelacavo.turismo2024.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.TagsService;

@RestController
public class SearchController<T extends Element> {
	
	@Autowired
	private ElementsService<Element> elementService;
	
	@Autowired
	private TagsService tagService;

	@PostMapping("/search/site")
	public RedirectView searchElementsSite(Model model, @RequestParam List<String> tag){
		List<String> toFind = tag;
		List<Element> toReturn = new ArrayList<Element>();
		toFind.stream().forEach(t -> toReturn.addAll(elementService.findByTagId(tagService.findByTag(t).getId())));
		model.addAttribute("listElements", toReturn);
		return new RedirectView("/elements");
	}
}
