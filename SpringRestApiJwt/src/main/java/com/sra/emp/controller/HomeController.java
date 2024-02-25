package com.sra.emp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sra.emp.model.User;
import com.sra.emp.services.UserSarvice;

@RestController
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private UserSarvice userSarvice;
	
	@GetMapping("/users")
	public List<User> getUser() {
		System.out.println("Getting User");
		return userSarvice.getUsers();
	}
	
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
	}
}
