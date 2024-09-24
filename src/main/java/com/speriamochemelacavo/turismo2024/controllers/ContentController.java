package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.content.Content;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ContentsService;
import com.speriamochemelacavo.turismo2024.services.ValidationsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/contents")
public class ContentController {
    @Autowired
    private LoggedUserDetailService loggedUserService;

    @Autowired
    private ContentsService contentService;
    
	@Autowired
	private ModelSetter modelSetter;

	@Autowired
	private ValidationsService<Content> validationService;
    
    @GetMapping("")
    public RedirectView getAllContents() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("toShow", contentService.findAll());
        return new RedirectView("/elements/list");
    }
    
    @GetMapping("/{id}")
    public RedirectView getContentById(@PathVariable int id) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
        try {
    		modelSetter.getAttributes().put("element", contentService.findById(id));
    		modelSetter.getAttributes().put("isContent", true);
			return new RedirectView("/element?id=" + id);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			modelSetter.getAttributes().put("alertMessage", "Contenuto non trovato!");
			return new RedirectView("/");
		}
    }

    @GetMapping("/creation")
    public RedirectView createContent(Content content) {
		modelSetter.getAttributes().put("urlCreationElement", "/contents/add");
        contentService.add(content);
        return new RedirectView("/contents");
    }

    @GetMapping("/imagefile")
    public ResponseEntity<Resource> getImageFile() throws Exception {
        String imageFilePath = "static/image.png/";
        URL url = getClass().getClassLoader().getResource(imageFilePath);
        if(url == null) {
            throw new IOException("File " + imageFilePath + " non trovato!");
        }
        File file = new File(url.toURI());
        Path path = Paths.get(url.toURI());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=customname.png");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(Files.readAllBytes(path)));
    }

    @PostMapping("/update")
    public RedirectView updateContent(@ModelAttribute Content element) {
        element.setAuthor(loggedUserService.getLoggedUser());
        Content toValidate = contentService.add(element);
        validationService.requestValidation(toValidate);
        contentService.add(toValidate);
        return new RedirectView("/contents/" + toValidate.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView deleteContentById(@PathVariable Integer id) {
        contentService.deleteById(id);
        return new RedirectView("/");
    }


}
