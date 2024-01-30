package ebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ebank.entity.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {

}
