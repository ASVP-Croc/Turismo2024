package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ContentsService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contents")
public class ContentController {
    @Autowired
    private LoggedUserDetailService loggedUserService;

    @Autowired
    private ContentsService contentService;
    
	@Autowired
	private ModelSetter modelSetter;
    
    @GetMapping("")
    public RedirectView getAllContents() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("toShow", contentService.findAll());
		modelSetter.getAttributes().put("isContent", true);
        return new RedirectView("/elements/list");
    }
    
    @GetMapping("/{id}")
    public RedirectView getContentById(@PathVariable int id) {
//    	TODO da ricontrollare, è stato fatto così il metodo per gestire temporaneamente l'eccezione
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		//    	TODO da ricontrollare, è stato fatto così il metodo per gestire temporaneamente l'eccezione
        try {
    		modelSetter.getAttributes().put("element", contentService.findById(id));
    		modelSetter.getAttributes().put("isContent", true);
			return new RedirectView("/element");
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			modelSetter.getAttributes().put("alertMessage", "Contenuto non trovato!");
			return new RedirectView("/");
		}
    }

    @GetMapping("/creation")
    public RedirectView createContent(Content content) {
		modelSetter.getAttributes().put("urlCreationElement", "/pois/add");
        contentService.add(content);
        return new RedirectView("/contents");
    }

    @PostMapping("/add")
    public void updateContent(@ModelAttribute Content element) {
		element.setAuthor(loggedUserService.getLoggedUser());
        contentService.add(element);
    }

    @DeleteMapping("/{id}")
    public void deleteContentById(@PathVariable Integer id) {
        contentService.deleteById(id);
    }


}
