package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.ElementStatus;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.ValidationsService;
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
        modelSetter.getAttributes().put("toShow", elementsService.findByValidated(ElementStatus.PENDING));
        elementsService.findByValidated(ElementStatus.PENDING).forEach(e -> System.out.println(e.toString()));;
        return new RedirectView("/validations");
    }

    @DeleteMapping
    public RedirectView deleteContent(T element) {

        return new RedirectView("/");
    }


}
