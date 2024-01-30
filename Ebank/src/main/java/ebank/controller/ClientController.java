package ebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ebank.entity.Client;
import ebank.entity.User;
import ebank.service.Service;
import ebank.service.UserService;

@Controller
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Service<Client> clientService;
	
	@GetMapping("/ajouter/{id:[0-9]+}")
	public String addClientGet(
			Model model,
			@PathVariable(name = "id") long id
			) {
		User user = userService.getById(id);
		Client client = new Client();
		client.setUser(user);
		 
		model.addAttribute("client", client);
		return "client/add";
	}
	@GetMapping("/mes-clients")
	public String addClientGet(
			
			) {
		return "client/list";
	}
	
	@PostMapping("/ajouter/{user:[0-9]+}")
	public String addClientPost(
			@ModelAttribute Client client,
			@AuthenticationPrincipal User currentUser
			) {
			client.setConseiller(currentUser.getConseiller());
			clientService.add(client);
		return "redirect:/clients/mes-clients";
	}
	
}
