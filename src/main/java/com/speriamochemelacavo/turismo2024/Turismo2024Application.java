package com.speriamochemelacavo.turismo2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
public class Turismo2024Application {

	public static void main(String[] args) {
		SpringApplication.run(Turismo2024Application.class, args);
	}

}
