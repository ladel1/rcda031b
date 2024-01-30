package ebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ebank.entity.Client;
import ebank.entity.Conseiller;
import ebank.entity.User;
import ebank.service.Service;

@Controller
public class PersonneController {

	@Autowired
	private Service<Conseiller> conseillerService;
	
	@GetMapping("/mon-profil")
	public String monProfil(
			Model model,
			@AuthenticationPrincipal User currentUser
			) {
		
		
		
		if(currentUser.getConseiller()!=null) {					
			model.addAttribute("profil", currentUser.getConseiller() );
		}else {
			Conseiller con = new Conseiller();
			con.setUser(currentUser);
			model.addAttribute("profil", con);
		}
		return "/profil/mon-profil";
	}
	
	@PostMapping("/mon-profil/{user:[0-9]+}")
	public String addClientPost(
			@ModelAttribute Conseiller profil,
			@AuthenticationPrincipal User currentUser
			) {						
			conseillerService.add(profil);
			currentUser.setConseiller(profil);
		return "redirect:/mon-profil";
	}
	
}
