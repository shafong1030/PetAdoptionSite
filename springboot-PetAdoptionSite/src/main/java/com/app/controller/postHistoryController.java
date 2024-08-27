package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.showPostDTO;
import com.app.service.userService;

@Controller
public class postHistoryController {
	@Autowired
	private userService ms;
	
	public postHistoryController(userService ms) {
		super();
		this.ms = ms;
	}
	
	//Firstly, show all the posts 
	@GetMapping("/postHistory")
	public String showPostsOfUser(Model model) {
		List<showPostDTO> posts = ms.showPostsOfUser();
		for(showPostDTO spDTO : posts) {
			System.out.println(spDTO.getAnimal());
		}
		model.addAttribute("posts", posts);
		return "postHistory";
	}
	
	@PostMapping("/postHistory/{postId}")
	public String changePostStatus(@PathVariable("postId") Long postId) {
		ms.changePostStatus(postId);
		return "redirect:/postHistory";

		
	}

}
