package ebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ebank.entity.Client;



public interface ClientRepository extends JpaRepository<Client, Long> {

}
