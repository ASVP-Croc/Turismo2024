package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ContestsService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/contests")
public class ContestController {
    @Autowired
    private LoggedUserDetailService loggedUserService;

    @Autowired
    private ElementsService<Contest> contestService;

    @GetMapping("/")
    public RedirectView getAllContests(Model model) {
		model.addAttribute("isContest", true);
		model.addAttribute("toShow", contestService.findAll());
        return new RedirectView("/elements/list");
    }
    
    @GetMapping("/{id}")
    public Contest getContestById(@PathVariable int id) {
        return contestService.findById(id);
    }

    @PostMapping("/creation")
    public RedirectView createContest(Contest contest) {
        contestService.add(contest, loggedUserService.getLoggedUser());
        return new RedirectView("/contests");
    }

    @PutMapping("/update")
    public void updateContest(@RequestBody Contest contestToUpdate) {
        contestService.add(contestToUpdate, loggedUserService.getLoggedUser());
    }

    @DeleteMapping("/{id}")
    public void deleteContestById(@PathVariable Integer id) {
        contestService.deleteById(id);
    }
}
