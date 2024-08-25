package controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("turismo2024")
public class ViewController {

	private String name;

	@GetMapping("/")
	public String index(@RequestParam(value = "name", required = false, defaultValue = "Turista") String name, Model model) {
		this.name = name;
		model.addAttribute("name", this.name);
		return "index";
	}
}
