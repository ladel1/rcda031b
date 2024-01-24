package com.eni.jeuxvideo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.eni.jeuxvideo.bll.AvisService;
import com.eni.jeuxvideo.bll.CategoryService;
import com.eni.jeuxvideo.bll.GameService;
import com.eni.jeuxvideo.bo.Avis;
import com.eni.jeuxvideo.bo.Game;


@Controller
public class GameController {

	@Autowired
	private GameService gameService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AvisService avisService;	
	
	@GetMapping("/jeux/ajouter")
	public String afficherFormulaire(Model model) {
		model.addAttribute("game", new Game());
		model.addAttribute("categories",categoryService.getAllCategories());
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
		Game game = gameService.selectionnerParId(id);
		model.addAttribute("game", game);
		model.addAttribute("listeAvis", avisService.trouverAvisParJeu(game) );
		model.addAttribute("avis",new Avis());
		model.addAttribute("moyenne", gameService.calculerMoyenneNotes(game));
		return "jeux/detail";
	}
	
	@PostMapping("/jeux/detail/{idGame:[0-9]+}")
	public String detailPostAvis( @PathVariable(name="idGame") Long idGame, 
									@ModelAttribute Avis avis) {
		// session 
		// validation !!!
		avis.setGame( gameService.selectionnerParId(idGame) );	
		avisService.ajouterAvis(avis);
		return "redirect:/jeux/detail/%d".formatted(idGame);
	}
	
	@GetMapping("/jeux/modifier/{id:[0-9]+}")
	public String afficherFormulaireModifier(@PathVariable(name="id") Long id,Model model) {
		model.addAttribute("game", gameService.selectionnerParId(id));
		model.addAttribute("categories",categoryService.getAllCategories());
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
