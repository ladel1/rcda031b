package com.eni.jeuxvideo.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eni.jeuxvideo.bo.Avis;
import com.eni.jeuxvideo.bo.Game;
import com.eni.jeuxvideo.dal.AvisDao;
import com.eni.jeuxvideo.dal.GameDao;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private AvisDao avisDao;
	
	@Override
	public void ajouter(Game game) {
		gameDao.save(game);
	}

	@Override
	public void modifier(Game game) {
		gameDao.save(game);
	}

	@Override
	public Game selectionnerParId(Long id) {
		return gameDao.getReferenceById(id);
	}

	@Override
	public List<Game> selectionnerTous() {		
		return gameDao.findAll();
	}

	@Override
	public void supprimer(Game game) {
		gameDao.delete(game);
	}

	@Override
	public float calculerMoyenneNotes(Game game) {
		List<Avis> listeAvis = avisDao.findByGame(game);
		float moyenne = 0;
		for (Avis avis : listeAvis) {
			moyenne += (float)avis.getNote()/listeAvis.size();
		}
		return moyenne;
	}

	@Override
	public List<Game> searchGames(String query) {
		return gameDao.searchByKeyword(query);
	}


}
