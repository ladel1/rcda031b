package ebank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ebank.entity.Role;
import ebank.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);	
}
