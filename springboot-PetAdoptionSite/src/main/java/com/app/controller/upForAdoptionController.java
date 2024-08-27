package com.app.controller;


import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.service.userService;

@Controller
@RequestMapping("/upForAdoption")
public class upForAdoptionController {
	private userService ms;

	public upForAdoptionController(userService ms) {
		super();
		this.ms = ms;
	}
	
	
	@GetMapping
	public String showupForAdoption() {
		System.out.println("show form");
		return "upForAdoption";
	}
	

	@PostMapping
	public String issuePost(@RequestParam("animal") String animal, @RequestParam("title") String title, @RequestParam("content") String content,
			                @RequestParam("image") MultipartFile image, Model model) throws IOException{
		try {
			ms.issuePost(animal, title, content, image);
			return "redirect:/upForAdoption?success";
		}catch(IOException e){
			e.printStackTrace();
			return "redirect:/upForAdoption?error";
		}
		
		}
	
	
	}

	
	
	
