package fr.eni.tp.filmotheque.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.tp.filmotheque.bo.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {

}
