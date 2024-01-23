package com.eni.jeuxvideo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.eni.jeuxvideo.bll.GameService;
import com.eni.jeuxvideo.bo.Game;


@Controller
public class GameController {

	@Autowired
	private GameService gameService;
	
	@GetMapping("/jeux/ajouter")
	public String afficherFormulaire(Model model) {
		model.addAttribute("game", new Game());
		return "jeux/ajouter";
	}
	
	@PostMapping("/jeux/ajouter")
	public String ajouter(@ModelAttribute Game game) {
		gameService.ajouter(game);
		//redirection vers la liste de jeux
		return "redirect:/jeux/";
	}
	
	@GetMapping("/jeux/")
	public String liste(Model model) {
		model.addAttribute("games",gameService.selectionnerTous());
		return "jeux/liste";
	}
	
	@GetMapping("/jeux/detail/{id:[0-9]+}")
	public String detail( @PathVariable(name="id") Long id, Model model ) {
		model.addAttribute("game", gameService.selectionnerParId(id));
		return "jeux/detail";
	}
	
	@GetMapping("/jeux/modifier/{id:[0-9]+}")
	public String afficherFormulaireModifier(@PathVariable(name="id") Long id,Model model) {
		model.addAttribute("game", gameService.selectionnerParId(id));
		return "jeux/modifier";
	}
	
	@PostMapping("/jeux/modifier/{id:[0-9]+}")
	public String modifier(@ModelAttribute Game game) {
		gameService.modifier(game);
		//redirection vers la liste de jeux
		return "redirect:/jeux/";
	}
	// CSRF
	@GetMapping("/jeux/supprimer/{id:[0-9]+}")
	public String supprimer(Game game) {
		gameService.supprimer(game);
		return "redirect:/jeux/";
	}
	
	
}
