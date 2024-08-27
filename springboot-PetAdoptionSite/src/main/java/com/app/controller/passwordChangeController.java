package com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.entity.user;
import com.app.repo.userRepo;
import com.app.service.userService;

@Controller
@RequestMapping("/infoChange")
public class passwordChangeController {
	
	private userService ms;
	@Autowired
	private userRepo repo;

	public passwordChangeController(userService ms) {
		super();
		this.ms = ms;
	}
	
	@GetMapping
	public String changeMemberInfo() {
		return "infoChange";
	}
	
	@PostMapping
	@PreAuthorize("hasRole('READ_PRIVILEGE')")
	public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
		user user = repo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (!ms.checkIfValidOldPassword(user, oldPassword)) {
			return "redirect:/infoChange?fail";
	    }else {
	    	ms.changeUserPassword(user, newPassword);
		    return "redirect:/infoChange?success";
	    }

	}
	
	
	


}
