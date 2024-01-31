package com.eni.jeuxvideo.bll;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.eni.jeuxvideo.bo.User;

public interface UserService extends UserDetailsService {
	void addUser(User user) throws Exception;
}	
