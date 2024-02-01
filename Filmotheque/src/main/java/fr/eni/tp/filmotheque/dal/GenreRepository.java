package fr.eni.tp.filmotheque.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.tp.filmotheque.bo.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
