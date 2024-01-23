package com.eni.jeuxvideo.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eni.jeuxvideo.bo.Game;
import com.eni.jeuxvideo.dal.GameDao;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDao gameDao;
	
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
	

}
