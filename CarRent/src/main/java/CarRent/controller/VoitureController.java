package CarRent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import CarRent.bll.Manager;
import CarRent.bo.Voiture;

@Controller
@RequestMapping("/voitures")
public class VoitureController {
	
	@Autowired
	private Manager<Voiture> voitureService;

	@GetMapping("/ajouter")
	public String affichagePageAjout(Model model) {
		model.addAttribute("voiture", new Voiture());
		return "voiture/ajouter";
	}
	@PostMapping("/ajouter")
	public String postPageAjout(@ModelAttribute  Voiture voiture) {
		System.err.println(voiture);
		voitureService.add(voiture);
		return "redirect:/voitures/";
	}
	
	@GetMapping("/modifier/{id:[0-9]+}")
	public String affichagePageModif(@PathVariable(name = "id") long id,Model model) {
		model.addAttribute("voiture", voitureService.getOne(id));
		return "voiture/modifier";		
	}
	
	@PostMapping("/modifier/{id:[0-9]+}")
	public String postModifier(@ModelAttribute  Voiture voiture) {		
		voitureService.edit(voiture);
		return "redirect:/voitures/";
	}
//	
	@GetMapping("/{id:[0-9]+}")
	public String detail(@PathVariable(name = "id") long id, Model model) {		
		model.addAttribute("voiture", voitureService.getOne(id));
		return "voiture/detail";
	}
	@GetMapping("/supprimer/{id:[0-9]+}")
	public String suppression(@PathVariable(name = "id") long id) {
		voitureService.delete(voitureService.getOne(id));
		return "redirect:/voitures/";
	}

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("voitures", voitureService.getAll());		
		return "voiture/liste";
	}
}
