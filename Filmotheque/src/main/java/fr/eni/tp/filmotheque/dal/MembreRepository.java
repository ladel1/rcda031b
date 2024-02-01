package fr.eni.tp.filmotheque.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.tp.filmotheque.bo.Membre;

public interface MembreRepository extends JpaRepository<Membre, Long> {

	Membre findByEmail(String username);

}
