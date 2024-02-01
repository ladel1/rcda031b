package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/*
 * Lombok ne marche pas très bien avec l'heritage
 * Il n'arrive pas à lire les attributs/méthodes de la classe mère
 * => il faut faire à l'ancienne
 */
@Entity
@Table(name = "PARTICIPANT")
public class Participant extends Personne{

	/*
	 * Constructeurs
	 */
	public Participant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Participant(String nom, String prenom) {
		super(nom, prenom);
		// TODO Auto-generated constructor stub
	}
	public Participant(long id, String nom, String prenom) {
		super(id, nom, prenom);
		// TODO Auto-generated constructor stub
	}
	
	

	
	
	

}
