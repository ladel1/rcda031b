package ebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ebank.entity.Role;
import ebank.service.UserService;

@RequestMapping("/users")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String list(Model model ) {		
		model.addAttribute("users", userService.getByRole(Role.ROLE_CLIENT));
		return "users/list";
	}
	
}
