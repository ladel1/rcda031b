package com.eni.jeuxvideo.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eni.jeuxvideo.bo.User;
import com.eni.jeuxvideo.dal.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return user;
	}

	@Override
	public void addUser(User user) throws Exception {
		validerPassword(user);
		user.setPassword( passwordEncoder.encode( user.getPassword() ) );
		userDao.save(user);
	}
	
	private void validerPassword(User user) throws Exception {
		if(user == null) throw new Exception("L'objet utilisateur est null");
		if(!user.getPassword().equals(user.getPlainPassword()))
			throw new Exception("Les deux mots de passe ne sont pas identiques!");
	}

}
