package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/contents")
public class ContentController {
    @Autowired
    private LoggedUserDetailService loggedUserService;

    @Autowired
    private ElementsService<Content> contentsService;

    @GetMapping("/{id}")
    public Content getContentById(@PathVariable int id) {
        return contentsService.findById(id);
    }

    @GetMapping("/all")
    public List<Content> getAllContent() {
        return contentsService.findAll();
    }

    @PostMapping("/creation")
    public RedirectView createContent(Content content) {
        contentsService.add(content, loggedUserService.getLoggedUser());
        return new RedirectView("/contents");
    }

    @PutMapping("/{id}")
    public void updateContent(@RequestBody Content contentToUpdate) {
        contentsService.add(contentToUpdate, loggedUserService.getLoggedUser());
    }

    @DeleteMapping("/{id}")
    public void deleteContentById(@PathVariable Integer id) {
        contentsService.deleteById(id);
    }


}
