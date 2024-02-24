package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;


@Controller
public class UserController {
	
	@Autowired
	private UserRepository repo;
   
	@GetMapping("/")
	public String RegistrationPage(Model model) {
		model.addAttribute("user",new Users());
		
		return "register";
	}
	
	@PostMapping("/register")
	public String PostUserDetails(Users user) {
		repo.save(user);
		
		return "redirect:/?success";
	}
	
	@GetMapping("/loginpage")
	public String LoginPage(Model model) {
		model.addAttribute("user",new Users());
		
	   return "login";
	}
	
}
