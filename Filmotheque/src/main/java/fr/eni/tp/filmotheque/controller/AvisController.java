package fr.eni.tp.filmotheque.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.security.UtilisateurSpringSecurity;

@Controller
//on va utiliser la même url dans @GetMapping et @PostMapping : autant metrte l'url UNE fois sur la classe avec @requestMapping
@RequestMapping("/films/{id}/avis") 
public class AvisController {
	
	/*
	 * On référencer le service dans un attribut
	 */
	private FilmService filmService;
	
	/*
	 * Le fait de créer un constructeur avec un argument 
	 * effectue un @Autowire automatique sur filmService
	 * => Spring va injecter dans l'attribut filmService la valeur d'un bean du contexte Spring qui implémente l'interface FilmService
	 * => Ne pas publier d'ajouter l'implémentation qu'on souhaite utiliser dans le contexte Spring (@Service)
	 */
	public AvisController(FilmService filmService) {
		super();
		this.filmService = filmService;
	}
	
	/**
	 * Je veux afficher le formulaire d'ajout d'avis à un film
	 * Je vais récupérer l'id du film à partir du chemin d'url (@PathVariable("id"))
	 */
	@GetMapping
	public String afficherFormulaireCreationAvis(@PathVariable("id") long id, Model model) {
		// je recupère le film grâce  au service
		Film film = filmService.consulterFilmParId(id);
		
		// j'ajoute l'attribut "film" (avec le film recuperé) qui sera accessible dans mon template
		model.addAttribute("film", film);
		// j'ajoute un objet vide Avis() qui sera referencé par le formulaire (avec th:object)
		model.addAttribute("avis", new Avis());
		
		// le template film.html va s'occuper de la géneration du HTML
		return "avis";
	}

	/**
	 * Est appelé lorsqu'on valide le formulaire d'ajout d'avis à un film
	 * Je vais récupérer :
	 *  - l'id du film à partir du chemin d'url (@PathVariable("id"))
	 *  - l'utilisateur connecté avec Spring Security grace à l'annotation : @AuthenticationPrincipal
	 *  - l'avis referencé par le formulaire (Avis avis)
	 */
	@PostMapping
	public String afficherFormulaireCreationAvis(Avis avis, @PathVariable("id") long id, @AuthenticationPrincipal UtilisateurSpringSecurity utilisateur) {
		// 1 - j'ajoute le membre connecté à l'avis
		avis.setMembre(utilisateur.getMembre());
		
		// 2 - j'appelle le service d'ajout d'avis à un film
		filmService.publierAvis(avis, id);
		
		// 3 - je redirige sur la page du film (prefixe redirect:/ fait en sorte que le navigateur envoie une nouvelle reqête HTTP de type GET sur la route specifiée)
		return "redirect:/films/" + id;
	}

}
