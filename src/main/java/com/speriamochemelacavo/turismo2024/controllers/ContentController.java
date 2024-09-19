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
    public Content getContentById(@PathVariable int id) {
        return contentService.findById(id);
    }

    @PostMapping("/creation")
    public RedirectView createContent(Content content) {
        contentService.add(content);
        return new RedirectView("/contents");
    }

    @PutMapping("/update")
    public void updateContent(@RequestBody Content contentToUpdate) {
        contentService.add(contentToUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteContentById(@PathVariable Integer id) {
        contentService.deleteById(id);
    }


}
