package CarRent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import CarRent.bll.Manager;
import CarRent.bll.VoitureManager;
import CarRent.bo.Client;
import CarRent.bo.Location;
import CarRent.bo.Voiture;

@Controller
@RequestMapping("/locations")
public class LocationController {
	@Autowired
	private Manager<Location> locationService;
	@Autowired
	private VoitureManager voitureService;
	@Autowired
	private Manager<Client> clientService;
	
	
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("locations", locationService.getAll());		
		return "location/liste";
	}

	@GetMapping("/ajouter")
	public String affichagePageAjout(Model model) {
		model.addAttribute("location", new Location());
		model.addAttribute("clients", clientService.getAll());
		model.addAttribute("voitures", voitureService.lesVoituresDispo());
		
		return "location/ajouter";
	}
	@PostMapping("/ajouter")
	public String postPageAjout(@ModelAttribute  Location location) {
		locationService.add(location);
		return "redirect:/locations/";
	}
}
