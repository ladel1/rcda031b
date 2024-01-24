package com.eni.jeuxvideo.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eni.jeuxvideo.bo.Avis;
import com.eni.jeuxvideo.bo.Game;

public interface AvisDao extends JpaRepository<Avis, Long> {
	List<Avis> findByGame(Game game);	
}
