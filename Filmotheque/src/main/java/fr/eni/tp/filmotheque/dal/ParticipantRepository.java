package fr.eni.tp.filmotheque.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.tp.filmotheque.bo.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
