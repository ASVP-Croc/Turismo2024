package com.speriamochemelacavo.turismo2024.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.TagsService;

@RestController
public class SearchController<T extends Element> {
	
	@Autowired
	private ElementsService<Element> elementService;
	
	@Autowired
	private TagsService tagService;

	@GetMapping("/search/site")
	public RedirectView searchElementsSite(Model model, @RequestParam String tag){
		List<Element> toReturn = new ArrayList<Element>();
		String tagClean = tag.replaceAll("\\s*,\\s*", ",");
		List<String> tagsList = Arrays.stream(tagClean.split(",")).filter(t -> !t.isEmpty()).collect(Collectors.toList());
		tagsList.stream().forEach(t -> toReturn.addAll(elementService.findByTag(tag)));
		model.addAttribute("listElements", toReturn);
		return new RedirectView("/elements");
	}
}
