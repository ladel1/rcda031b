package ebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ebank.entity.Conseiller;
import ebank.entity.User;

public interface ConseillerRepository extends JpaRepository<Conseiller, Long> {

	Conseiller findByUser(User user);
	
}
