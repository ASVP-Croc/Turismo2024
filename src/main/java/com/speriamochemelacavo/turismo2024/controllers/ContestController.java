package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.category.ElementTypology;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ContestsService;
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
@RequestMapping("/contests")
public class ContestController {
    @Autowired
    private LoggedUserDetailService loggedUserService;

    @Autowired
    private ContestsService contestService;
    
	@Autowired
	private ModelSetter modelSetter;

    @GetMapping("")
    public RedirectView getAllContests() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("toShow", contestService.findAll());
		modelSetter.getAttributes().put("isContest", true);
        return new RedirectView("/elements/list");
    }
    
    @GetMapping("/{id}")
    public RedirectView getContestById(@PathVariable int id) {
//    	TODO da ricontrollare, è stato fatto così il metodo per gestire temporaneamente l'eccezione
        try {
			contestService.findById(id);
			return new RedirectView("element");
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return new RedirectView("element");
		}
    }

    @GetMapping("/creation")
    public RedirectView createContest(Contest contest) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("isContest", true);
        return new RedirectView("/creation");
    }

    @PutMapping("/update")
    public void updateContest(@RequestBody Contest contestToUpdate) {
        contestService.add(contestToUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteContestById(@PathVariable Integer id) {
        contestService.deleteById(id);
    }
}
