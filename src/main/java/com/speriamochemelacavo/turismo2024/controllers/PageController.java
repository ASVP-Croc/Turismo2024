package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageController {
	
	@RequestMapping("/")
	public String welcome() {
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
	 return "login";
	}

}
