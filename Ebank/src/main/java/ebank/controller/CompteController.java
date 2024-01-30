package ebank.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ebank.entity.Client;
import ebank.entity.Compte;
import ebank.entity.User;
import ebank.service.CompteService;
import ebank.service.Service;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/comptes")
public class CompteController {

	@Autowired
	private CompteService compteService;
	
	@Autowired
	private Service<Client> clientService;	
	
	@PostMapping("/ajouter")
	public String ajouterCompte(@RequestParam(name = "client_id") long client_id ) {
		
		Client client = clientService.getById(client_id);
		Compte compte = new Compte();
		compte.setNumCompte("FR"+UUID.randomUUID().toString().substring(0, 8));
		compte.setClient(client);
		compteService.add(compte);
		return "redirect:/clients/mes-clients";
	}
	
	
	@GetMapping("/crediter/{id:[0-9]+}")
	public String crediter(@PathVariable("id") long id, Model model) {
		model.addAttribute("compte", clientService.getById(id).getCompte());
		return "compte/crediter";
	}
	
	@PostMapping("/crediter/{client:[0-9]+}")
	public String crediter(
			@ModelAttribute Compte compte,
			@RequestParam(name = "montant") float montant) {

		compteService.crediter(compte, montant);
		return "redirect:/comptes/crediter/%d".formatted(compte.getClient().getId());
	}
	
	@GetMapping("/debiter")
	public String debiter( Model model,
			@AuthenticationPrincipal User currentUser
			) {
		long idClient = currentUser.getClient().getId();
		model.addAttribute("compte", clientService.getById(idClient).getCompte());
		return "compte/debiter";
	}
	
	@PostMapping("/debiter/{client:[0-9]+}")
	public String debiter(
			@ModelAttribute Compte compte,
			@RequestParam(name = "montant") float montant,
			Model model
			) {
		
		try {
			compteService.debiter(compte, montant);
		} catch (Exception e) {
			model.addAttribute("erreur", e.getMessage());
			return "compte/debiter";
		}
		return "redirect:/comptes/debiter";
	}
	@GetMapping("/virement")
	public String virement( Model model,
			@AuthenticationPrincipal User currentUser
			) {
		long idClient = currentUser.getClient().getId();
		model.addAttribute("clients", clientService.getAll());
		model.addAttribute("compte", clientService.getById(idClient).getCompte());
		return "compte/virement";
	}
	
	@PostMapping("/virement/{client:[0-9]+}")
	public String virement(
			@ModelAttribute Compte compte,
			@RequestParam(name = "montant") float montant,
			@RequestParam(name = "bene") Compte beneficiaire,
			Model model
			) {
		
		
		try {
			compteService.virement(compte,beneficiaire, montant);
		} catch (Exception e) {
			model.addAttribute("clients", clientService.getAll());
			model.addAttribute("erreur", e.getMessage());
			return "compte/virement";
		}
		return "redirect:/comptes/virement";
	}
	
	
	
}
