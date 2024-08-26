package com.speriamochemelacavo.turismo2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@SpringBootApplication
public class Turismo2024Application {

	public static void main(String[] args) {
		SpringApplication.run(Turismo2024Application.class, args);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<Object> getPOIs(){
	    return new ResponseEntity<>("Hello world!", HttpStatus.OK);
	}
	


}
