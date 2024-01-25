package CarRent.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import CarRent.bo.Voiture;

public interface VoitureDao extends JpaRepository<Voiture, Long> {

	@Query(value="SELECT v FROM Voiture v LEFT JOIN v.locations l WHERE l IS NULL OR l.dateRetour < CURRENT_DATE")
	List<Voiture> trouverVoituresDispo();
	
}
