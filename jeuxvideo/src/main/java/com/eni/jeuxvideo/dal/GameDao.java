package com.eni.jeuxvideo.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eni.jeuxvideo.bo.Game;

public interface GameDao extends JpaRepository<Game, Long> {

}
