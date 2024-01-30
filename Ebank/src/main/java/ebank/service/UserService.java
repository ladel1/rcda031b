package ebank.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ebank.entity.Role;
import ebank.entity.User;


public interface UserService extends UserDetailsService {
	void addUser(User user);
	List<User> getByRole(Role role);
	User getById(long id);
}	
