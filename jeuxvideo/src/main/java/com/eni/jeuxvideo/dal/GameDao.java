package com.eni.jeuxvideo.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eni.jeuxvideo.bo.Game;

public interface GameDao extends JpaRepository<Game, Long> {

	//JPQL
	@Query(value="SELECT * FROM games g WHERE g.titre LIKE %?1% OR g.description LIKE %?1%",
			nativeQuery = true
			)
	List<Game> searchByKeyword(String query);

}
