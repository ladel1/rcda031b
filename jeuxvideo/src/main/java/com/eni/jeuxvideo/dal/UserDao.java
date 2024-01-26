package com.eni.jeuxvideo.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eni.jeuxvideo.bo.User;

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
