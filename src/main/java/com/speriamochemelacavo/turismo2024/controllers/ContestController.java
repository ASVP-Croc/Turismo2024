package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.exception.ElementNotFoundException;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.ElementStatus;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

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
	private ValidationsService<Contest> validationService;

	@Autowired
	private TagsService tagService;

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
    
    @GetMapping("/{id}")
    public RedirectView getContestById(@PathVariable int id) {
//    	TODO da ricontrollare, è stato fatto così il metodo per gestire temporaneamente l'eccezione
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		//    	TODO da ricontrollare, è stato fatto così il metodo per gestire temporaneamente l'eccezione
        try {
    		modelSetter.getAttributes().put("element", contestService.findById(id));
    		modelSetter.getAttributes().put("isContest", true);
			return new RedirectView("/element?id=" + id);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			modelSetter.getAttributes().put("alertMessage", "Contest non trovato!");
			return new RedirectView("/");
		}

	}

    @GetMapping("/creation")
    public RedirectView createContest(Contest contest) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("isContest", true);
		modelSetter.getAttributes().put("urlCreationElement", "/contests/add");
		modelSetter.getAttributes().put("element", new Contest());
        return new RedirectView("/creation");
    }

    @PostMapping("/add")
    public RedirectView addContest(@ModelAttribute Contest element) {
		element.setAuthor(loggedUserService.getLoggedUser());
		Contest toValidate = contestService.add(element);
		tagService.addAll(tagService.createTagsFromString(
				element.getName() + "," +
				element.getDescription() + "," +
				element.getTheme() + "," +
				element.getCity(), element));
		validationService.requestValidation(toValidate);
		contestService.add(toValidate);
		return new RedirectView("/contests/" + toValidate.getId());
    }

	@PutMapping("/update")
	public RedirectView updateContest(@PathVariable int id) throws SQLIntegrityConstraintViolationException {
		Contest contest = contestService.findById(id);
		Contest toValidate = contestService.update(contest);
		tagService.addAll(tagService.createTagsFromString(
				contest.getName() + "," +
						contest.getDescription() + "," +
						contest.getTheme() + "," +
						contest.getCity(), contest));
		validationService.requestValidation(toValidate);
		contestService.add(toValidate);
		return new RedirectView("/contests/" + toValidate.getId());
	}

	@PutMapping("/update/status")
	public RedirectView updateTourStatus(int id, @RequestBody ElementStatus elementStatus) throws ElementNotFoundException {
		Contest contest = contestService.updateStatus(id, elementStatus);
		return new RedirectView("/contests/" + contest.getId());
	}

    @DeleteMapping("/delete/{id}")
    public RedirectView deleteContestById(@PathVariable Integer id) {
        contestService.deleteById(id);
		return new RedirectView("/");
    }
}
