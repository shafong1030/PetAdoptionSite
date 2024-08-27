package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetAdoptionSite {

	public static void main(String[] args) {
		SpringApplication.run(PetAdoptionSite.class, args);
		System.out.println("My application is running on...");
	}

}
