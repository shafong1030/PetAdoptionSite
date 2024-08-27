package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.showPostDTO;
import com.app.service.userService;

@EnableWebSocketSecurity
@Controller
public class postsController {
	@Autowired
	private userService ms;
	
	public postsController(userService ms) {
		super();
		this.ms = ms;
	}
	
	//Firstly, show all the posts 
	@GetMapping("/posts")
	public String showPostWithStatusTrue(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
		System.out.println(keyword);
		List<showPostDTO> posts = ms.showPostWithStatusTrue("");
		model.addAttribute("keyword", keyword);
		model.addAttribute("posts", posts);
		return "posts";
	}
    
	//After the user submit the search keyword, show the search result
	@PostMapping("/posts")
	public String showPostWithStatusTrueAndKeyword(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
		model.addAttribute("keyword", keyword);
		System.out.println(keyword);
		List<showPostDTO> posts = ms.showPostWithStatusTrue(keyword);
		model.addAttribute("posts", posts);
		return "posts";
	}
	
    
}
	
		



