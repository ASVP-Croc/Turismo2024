package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.services.AccountsService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	AccountsService accountService;
	
	@PostMapping("/send")
	public RedirectView loginByUserName(String userName){
		accountService.saveLoggedUser(userName);
        return new RedirectView("/");
	}
}
