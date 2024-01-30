package ebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ebank.entity.Role;
import ebank.entity.User;
import ebank.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return user;
	}

	@Override
	public void addUser(User user) {
		user.setPassword( passwordEncoder.encode( user.getPassword() ) );
		userRepository.save(user);
	}

	@Override
	public List<User> getByRole(Role role) {
		List<User>  users = userRepository.findAll();
		for(int i=0; i< users.size(); i++){
			if(!users.get(i).getRoles().contains(role)) {
				users.remove(i);
			}
		};	
		//System.out.println(users);
		return users;
	}

	@Override
	public User getById(long id) {
		// TODO Auto-generated method stub
		return userRepository.getReferenceById(id);
	}

}
