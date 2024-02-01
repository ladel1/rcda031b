package fr.eni.tp.filmotheque.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;

@Controller
public class FilmController {
	
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
	public FilmController(FilmService filmService) {
		super();
		this.filmService = filmService;
	}
	
	/**
	 * Plutôt que d'ajouter model.addAttribute("genres", filmService.consulterGenres());
	 * dans chaque méthode, je peux le définir globalement au niveau de mon controller
	 * en utilisant l'annotation @ModelAttribute
	 */
	@ModelAttribute("genres")
	public List<Genre> getGenre(){
		return filmService.consulterGenres();
	}
	
	/**
	 * Plutôt que d'ajouter model.addAttribute("participants", filmService.consulterParticipants());
	 * dans chaque méthode, je peux le définir globalement au niveau de mon controller
	 * en utilisant l'annotation @ModelAttribute
	 */
	@ModelAttribute("participants")
	public List<Participant> getParticipant(){
		return filmService.consulterParticipants();
	}
	
	/**
	 * Je veux afficher les films dans mon template film.html => je vais retourner le nom du template : "film"
	 * Je vais récupérer l'id du film à partiur du chemin d'url (@PathVariable("id"))
	 * pour pouvoir afficher la liste de films dans mon template, j'ajoute un attribut au model
	 */
	@GetMapping("/films/{id}")
	public String afficherUnFilm(@PathVariable("id") long id, Model model) {
		// je recupère le film grâce  au service
		Film film = filmService.consulterFilmParId(id);
		
		// j'ajoute l'attribut "film" (avec le film recuperé) qui sera accessible dans mon template
		model.addAttribute("film", film);
		
		// le template film.html va s'occuper de la géneration du HTML
		// il aura accès à la variable du model :  ${film} 
		return "film";
	}
	
	/**
	 * Je veux afficher les films dans mon template film.html => je vais retourner le nom du template : "film"
	 * Je vais récupérer l'id du film à partiur du chemin d'url (@PathVariable("id"))
	 * pour pouvoir afficher la liste de films dans mon template, j'ajoute un attribut au model
	 */
	@GetMapping("/film/creer")
	public String afficherFormulaireCreationFilm(Model model) {
		// je vais ajouter au modèle un film vide qui sera referencé par le formulaire avec th:object
		model.addAttribute("film", new Film());
		
		// le template filmCreation.html va s'occuper de la géneration du HTML
		// il aura accès à la variable du model :  ${film} 
		return "filmCreation";
	}
	
	/**
	 * Est appelé lorsqu'on valide le formulaire d'ajout de film
	 * Je vais récupérer le film referencé par le formulaire (Film film)
	 */
	@PostMapping("/film/creer")
	public String afficherFormulaireCreationFilm(Film film) {
		
		System.out.println("film : " + film);
		
		// 1 - je créer le film en appelant le service
		filmService.creerFilm(film);
		
		// 2 - je redirige sur la page des film (prefixe redirect:/ fait en sorte que le navigateur envoie une nouvelle reqête HTTP de type GET sur la route specifiée)
		return "redirect:/films";
	}

	/**
	 * Je veux afficher les films dans mon template films.html => je vauis retouyrner le nom du template : "films"
	 * pour pouvoir afficher la liste de films dans mon template, j'ajoute un attribut au model
	 */
	@GetMapping("/films")
	public String afficherFilms(Model model) {
		// je recupère les films grâce au service
		List<Film> films = filmService.consulterFilms();
		
		// j'ajoute l'attribut "listeFilms" (avec les films recuperés) qui sera accessible dans mon template
		model.addAttribute("listeFilms", films);
		
		// le template films.html va s'occuper de la géneration du HTML
		// il aura accès à la variable du model :  ${listeFilms} 
		return "films";
	}

}
