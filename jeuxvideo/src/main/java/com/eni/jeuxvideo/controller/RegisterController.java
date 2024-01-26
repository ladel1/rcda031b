package com.eni.jeuxvideo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.eni.jeuxvideo.bll.UserService;
import com.eni.jeuxvideo.bo.Role;
import com.eni.jeuxvideo.bo.User;

import jakarta.validation.Valid;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/inscription")
	public String index(Model model) {
		model.addAttribute("user", new User());	
		model.addAttribute("roles", Role.values());
		return "security/register";
	}
	
	
	@PostMapping("/inscription")
	public String inscription(@Valid @ModelAttribute User user,BindingResult br,Model model) {
		
		if(br.hasErrors()) {
			model.addAttribute("roles", Role.values());
			return "security/register";
		}
		
		userService.addUser(user);		
		return "redirect:/login";
	}
	
}
