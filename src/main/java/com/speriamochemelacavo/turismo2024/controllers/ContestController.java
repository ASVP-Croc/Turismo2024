package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ContestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/contests")
public class ContestController {
    @Autowired
    private LoggedUserDetailService loggedUserService;

    @Autowired
    private ContestsService contestsService;

    @GetMapping("/{id}")
    public Contest getContestById(@PathVariable int id) {
        return contestsService.findById(id);
    }

    @GetMapping("/all")
    public List<Contest> getAllContest() {
        return contestsService.findAll();
    }

    @PostMapping("/creation")
    public RedirectView createContest(Contest contest) {
        contestsService.add(contest, loggedUserService.getLoggedUser());
        return new RedirectView("/contests");
    }

    @PutMapping("/{id}")
    public void updateContest(@RequestBody Contest contestToUpdate) {
        contestsService.add(contestToUpdate, loggedUserService.getLoggedUser());
    }

    @DeleteMapping("/{id}")
    public void deleteContestById(@PathVariable Integer id) {
        contestsService.deleteById(id);
    }
}
