package com.eni.jeuxvideo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "security/login";
	}
	
	@GetMapping("/login-error")
	public String login(Model model) {
		model.addAttribute("loginError", true);
		return "security/login";
	}
	
}
