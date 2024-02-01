package fr.eni.tp.filmotheque.bll;

import java.util.List;

import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;

/**
 * Spécifie les fonctionnalités qui doivent être implémenter dans les classes de service 
 */
public interface FilmService {
	
	public List<Film> consulterFilms();
	
	public Film consulterFilmParId(long id);
	
	public List<Genre> consulterGenres();
	
	public List<Participant> consulterParticipants();
	
	public Genre consulterGenreParId(long id);
	
	public Participant consulterParticipantParId(long id);
	
	public void creerFilm(Film film);

	List<Avis> consulterAvis(long idFilm);

	void publierAvis(Avis avis, long idFilm);

	String consulterTitreFilm(long id);
}
