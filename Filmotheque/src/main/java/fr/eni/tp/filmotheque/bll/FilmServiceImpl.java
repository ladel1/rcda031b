package fr.eni.tp.filmotheque.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.AvisRepository;
import fr.eni.tp.filmotheque.dal.FilmRepository;
import fr.eni.tp.filmotheque.dal.GenreRepository;
import fr.eni.tp.filmotheque.dal.ParticipantRepository;

@Service
@Primary
public class FilmServiceImpl implements FilmService {
	@Autowired 
	private FilmRepository filmRepository;
	@Autowired 
	private GenreRepository genreRepository;
	@Autowired 
	private ParticipantRepository participantRepository;
	@Autowired 
	private AvisRepository avisRepository;
	
	@Override
	public List<Film> consulterFilms() {
		// TODO Auto-generated method stub
		return filmRepository.findAll();
	}

	@Override
	public Film consulterFilmParId(long id) {		
		return filmRepository.getReferenceById(id);
	}

	@Override
	public List<Genre> consulterGenres() {
		// TODO Auto-generated method stub
		return genreRepository.findAll();
	}

	@Override
	public List<Participant> consulterParticipants() {
		// TODO Auto-generated method stub
		return participantRepository.findAll();
	}

	@Override
	public Genre consulterGenreParId(long id) {
		// TODO Auto-generated method stub
		return genreRepository.getReferenceById(id);
	}

	@Override
	public Participant consulterParticipantParId(long id) {
		// TODO Auto-generated method stub
		return participantRepository.getReferenceById(id);
	}

	@Override
	public void creerFilm(Film film) {
		filmRepository.save(film);
	}

	@Override
	public List<Avis> consulterAvis(long idFilm) {		
		return avisRepository.findByFilmId(idFilm);
	}

	@Override
	public void publierAvis(Avis avis, long idFilm) {
		Film film = new Film();
		film.setId(idFilm);
		avis.setFilm(film);
		avisRepository.save(avis);
	}

	@Override
	public String consulterTitreFilm(long id) {		
		return filmRepository.getReferenceById(id).getTitre();
	}

}
