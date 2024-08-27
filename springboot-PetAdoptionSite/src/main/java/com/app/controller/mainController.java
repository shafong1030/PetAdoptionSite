package com.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

	
	@GetMapping("/login")
	public String login() {
		System.out.println("show login")	;
		return "/login";		
	}
	
	@GetMapping("/")
	public String home() {
		System.out.println("Locate to Home page.")	;
		return "/index";
	}
	
	
	@GetMapping("/memberInfo")
	public String memberInformation() {
		System.out.println("Locate to member Information page.")	;
		return "/memberInfo";
	}
	
}
