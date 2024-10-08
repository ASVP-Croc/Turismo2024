package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.exception.ElementNotFoundException;
import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.ElementStatus;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/contests")
public class ContestController {

    @Autowired
    private ContestsService contestService;
    
	@Autowired
	private ModelSetter modelSetter;

	@Autowired
	private LoggedUserDetailService loggedUserService;

	@Autowired
	private ValidationsService validationService;

	@Autowired
	private TagsService tagService;

	@Autowired
	private ContentsService contentService;

    @GetMapping("")
    public RedirectView getAllContests() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		List<Contest> toReturn;
		try {
			toReturn = contestService.findByValidated(ElementStatus.APPROVED);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			toReturn = new ArrayList<>();
		}
		modelSetter.getAttributes().put("toShow", toReturn);
        return new RedirectView("/elements/site/list");
    }

    @GetMapping("/creation")
    public RedirectView createContest(Contest contest) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("isSite", true);
		modelSetter.getAttributes().put("isContest", true);
		modelSetter.getAttributes().put("urlCreationElement", "/contests/add");
        return new RedirectView("/creation");
    }

    @PostMapping("/add")
    public RedirectView addContest(@ModelAttribute Contest element) {
    	modelSetter.clearAllAttributes();
    	modelSetter.setBaseVisibility();
    	Set<Tag> toCompare = tagService.createTagsFromString(
				element.getName() + "," +
				element.getDescription() + "," +
				element.getCity(), element);
		for (Tag tag : toCompare) {
			try {
				tagService.findByTag(tag.getTagName());
			} catch (SQLIntegrityConstraintViolationException e) {
				e.printStackTrace();
				element.setAuthor(loggedUserService.getLoggedUser());
				Contest toValidate = contestService.add(element);
				tagService.addAll(toCompare);
				validationService.requestValidation(toValidate);
				contestService.add(toValidate);
				return new RedirectView("/contests/" + toValidate.getId());
			}
		}
		return new RedirectView("/contests/creation");
    }
    
	@PostMapping("/addWithContent")
	public RedirectView addContestWithContent(@ModelAttribute Contest element,
			@RequestParam MultipartFile file,
			@ModelAttribute Content content) {
		RedirectView toReturn = addContest(element);
		content.setAuthor(loggedUserService.getLoggedUser());
		try {
			Content toValidate = contentService.add(content, element, file);
			validationService.requestValidation(toValidate);
			contentService.add(content);
		} catch (IOException e) {
			e.printStackTrace();
			modelSetter.getAttributes().put("alertMessage", e.getMessage());
		}
		return toReturn;
	}

	@PostMapping("/update")
	public RedirectView updateContest(@ModelAttribute Contest element) {
		tagService.addAll(tagService.createTagsFromString(
				element.getName() + "," +
						element.getDescription() + "," +
						element.getTheme() + "," +
						element.getCity(), element));
		validationService.requestValidation(element);
		contestService.add(element);
		
		return new RedirectView("/contests/" + element.getId());
	}

	@PutMapping("/update/status")
	public RedirectView updateContestStatus(int id, @RequestBody ElementStatus elementStatus) throws ElementNotFoundException {
		Contest contest = contestService.updateStatus(id, elementStatus);
		return new RedirectView("/contests/" + contest.getId());
	}

    @DeleteMapping("/delete/{id}")
    public RedirectView deleteContestById(@PathVariable Integer id) {
        contestService.deleteById(id);
		return new RedirectView("/");
    }
}
