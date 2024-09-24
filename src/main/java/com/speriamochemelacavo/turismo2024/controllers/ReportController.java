package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/report")
public class ReportController<T extends Element> {
    @Autowired
    private ElementsService<T> elementsService;

    @Autowired
    private ReportsService<T> reportsService;

    @Autowired
    private ModelSetter modelSetter;

    @GetMapping("/{id}")
    public RedirectView getReport(@PathVariable int idElement){
        modelSetter.clearAllAttributes();
        modelSetter.setBaseVisibility();
        try {
            modelSetter.getAttributes().put("Element", elementsService.findById(idElement));
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        return new RedirectView("/element/report");
    }

    @PostMapping
    public RedirectView sendReport(T element, String message) {
        reportsService.reportElement(element, message);
        return new RedirectView("/");
    }
}
