package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ContentsService;
import com.speriamochemelacavo.turismo2024.services.ValidationsService;

import jakarta.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    	modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("urlCreationElement", "/contents/add");
        return new RedirectView("/creation");
    }
    
    

//    @GetMapping("/imagefile")
//    public ResponseEntity<Resource> getImageFile() throws Exception {
//        String imageFilePath = "static/image.png/";
//        URL url = getClass().getClassLoader().getResource(imageFilePath);
//        if(url == null) {
//            throw new IOException("File " + imageFilePath + " non trovato!");
//        }
//        File file = new File(url.toURI());
//        Path path = Paths.get(url.toURI());
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=customname.png");
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(file.length())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(new ByteArrayResource(Files.readAllBytes(path)));
//    }

    public void addContent(@ModelAttribute Content content, @ModelAttribute ElementWithContents element, @RequestParam MultipartFile file, @RequestParam String filename) throws IOException {
        content.setAuthor(loggedUserService.getLoggedUser());
        content.setReferenced(element);
        Content toValidate = contentService.add(content);
        validationService.requestValidation(toValidate);
        contentService.add(toValidate);
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView deleteContentById(@PathVariable Integer id) {
        contentService.deleteById(id);
        return new RedirectView("/");
    }
    
    @GetMapping("/download")
    public ResponseEntity<Object> fileDownload(@PathParam("filename") String filename) throws FileNotFoundException {
     String fileN="src/main/resources/"+filename;
     File file=new File(fileN);
        InputStreamResource resource=new InputStreamResource(new FileInputStream(file));
        HttpHeaders header=new HttpHeaders();
        header.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        header.add("Cache-control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires","0");
        ResponseEntity<Object> response=ResponseEntity.ok().headers(header).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);
        return response;
    }


}
