package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.ElementStatus;
import com.speriamochemelacavo.turismo2024.services.ElementsService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/validation")
public class ValidationController<T extends Element> {

    @Autowired
    private ModelSetter modelSetter;

    @Autowired
    private ElementsService<T> elementsService;

    public ValidationController() {

    }

    @GetMapping("")
    public RedirectView getValidation(Model model) {
        modelSetter.clearAllAttributes();
        modelSetter.setBaseVisibility();
        List<T> toReturn = new ArrayList<>();
        try {
			toReturn = elementsService.findByValidated(ElementStatus.PENDING);
			modelSetter.getAttributes().put("toShow", toReturn);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} finally {
	        toReturn.forEach(e -> System.out.println(e.toString()));;
		}
        return new RedirectView("/validations");
    }


}
