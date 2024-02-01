package fr.eni.tp.filmotheque.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.eni.tp.filmotheque.bo.Avis;

public interface AvisRepository extends JpaRepository<Avis, Long> {

	@Query(value="SELECT * FROM avis a WHERE a.id_film = ?1"  ,nativeQuery = true )
	List<Avis> findByFilmId(long filmId);
	
}
