package com.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.newRegUserDTO;
import com.app.service.userService;

@Controller
@RequestMapping("/NewReg")
public class newRegController {
	private userService ms;

	public newRegController(userService ms) {
		super();
		this.ms = ms;
	}
	
	@ModelAttribute("user")
	public newRegUserDTO nrmDTO() {
		return new newRegUserDTO();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "NewReg";
	}
	
	@PostMapping
	public String registeruserAccountl(@ModelAttribute("user") newRegUserDTO nrmDTO) {
		if(ms.checkEmail(nrmDTO)) {
			return "redirect:/NewReg?fail";
		}else {
			ms.save(nrmDTO);
			return "redirect:/NewReg?success";
		}
		
	}
	
	
	
	}
