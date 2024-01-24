package com.eni.jeuxvideo.bll;

import java.util.List;

import com.eni.jeuxvideo.bo.Avis;
import com.eni.jeuxvideo.bo.Game;

public interface AvisService {

	List<Avis> trouverAvisParJeu(Game game);
	void ajouterAvis(Avis avis);	
	//void supprimerAvis(Avis avis);
	
	
}
