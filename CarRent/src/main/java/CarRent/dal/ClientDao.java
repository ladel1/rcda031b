package CarRent.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import CarRent.bo.Client;

public interface ClientDao extends JpaRepository<Client,Long> {

}
