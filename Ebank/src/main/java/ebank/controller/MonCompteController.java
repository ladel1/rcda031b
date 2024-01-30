package ebank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonCompteController {

	@GetMapping("/mon-compte")
	public String monCompte() {
		return "backoffice/mon-compte";
	}
	
	
}
