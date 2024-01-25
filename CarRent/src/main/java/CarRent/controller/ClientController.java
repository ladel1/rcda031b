package CarRent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import CarRent.bll.Manager;
import CarRent.bo.Client;


@Controller
@RequestMapping("/clients")
public class ClientController {
	@Autowired
	private Manager<Client> clientService;

	
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("clients", clientService.getAll());		
		return "client/liste";
	}

	@GetMapping("/ajouter")
	public String affichagePageAjout(Model model) {
		model.addAttribute("client", new Client());
		return "client/ajouter";
	}
	@PostMapping("/ajouter")
	public String postPageAjout(@ModelAttribute  Client client) {
		clientService.add(client);
		return "redirect:/clients/";
	}
	
}
