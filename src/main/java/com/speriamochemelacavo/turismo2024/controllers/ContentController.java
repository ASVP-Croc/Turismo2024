package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contents")
public class ContentController {
    @Autowired
    private LoggedUserDetailService loggedUserService;

    @Autowired
    private ElementsService<Content> contentService;
    
	@Autowired
	private PageController pageController;
    
    @GetMapping("/")
    public RedirectView getAllContents(Model model) {
    	
    	HashMap<String, ?> toAdd = (HashMap<String, ?>) Map.ofEntries(
    			Map.entry("isContent", true), 
    			Map.entry("toShow", contentService.findAll()));
    	
    	pageController.addAllAttributesToModel(model, toAdd);
        return new RedirectView("/elements/list");
    }
    
    @GetMapping("/{id}")
    public Content getContentById(@PathVariable int id) {
        return contentService.findById(id);
    }

    @PostMapping("/creation")
    public RedirectView createContent(Content content) {
        contentService.add(content, loggedUserService.getLoggedUser());
        return new RedirectView("/contents");
    }

    @PutMapping("/update")
    public void updateContent(@RequestBody Content contentToUpdate) {
        contentService.add(contentToUpdate, loggedUserService.getLoggedUser());
    }

    @DeleteMapping("/{id}")
    public void deleteContentById(@PathVariable Integer id) {
        contentService.deleteById(id);
    }


}
