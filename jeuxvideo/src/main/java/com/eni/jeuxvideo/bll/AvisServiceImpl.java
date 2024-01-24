package com.eni.jeuxvideo.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eni.jeuxvideo.bo.Avis;
import com.eni.jeuxvideo.bo.Game;
import com.eni.jeuxvideo.dal.AvisDao;

@Service
public class AvisServiceImpl implements AvisService {

	@Autowired
	private AvisDao avisDao; 
	
	@Override
	public List<Avis> trouverAvisParJeu(Game game) {		
		return avisDao.findByGame(game);
	}

	@Override
	public void ajouterAvis(Avis avis) {
		avisDao.save(avis);
	}

}
