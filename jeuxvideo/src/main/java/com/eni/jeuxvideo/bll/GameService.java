package com.eni.jeuxvideo.bll;

import java.util.List;

import com.eni.jeuxvideo.bo.Game;

public interface GameService {

	void ajouter(Game game);
	void modifier(Game game);
	Game selectionnerParId(Long id);
	List<Game> selectionnerTous();
	void supprimer(Game game);
	float calculerMoyenneNotes(Game game);
	List<Game> searchGames(String query);
}
