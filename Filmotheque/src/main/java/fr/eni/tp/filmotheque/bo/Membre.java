package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// callSuper = true : appelle la méthode du parent
@Getter @Setter @EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "MEMBRE")
public class Membre extends Personne{
	
	@Column(nullable = false)
	private String email;
	
	@ToString.Exclude // on ne veut pas afficher le mot de passe dans le toString()
	@Column(name = "password",nullable = false)
	private String motDePasse;
	
	
	private boolean admin;

	
	/*
	 * Constructeurs
	*/
	public Membre(String nom, String prenom, String email, String motDePasse, boolean admin) {
		super(nom, prenom);
		this.email = email;
		this.motDePasse = motDePasse;
		this.admin = admin;
	}

	public Membre(long id, String nom, String prenom, String email, String motDePasse) {
		super(id, nom, prenom);
		this.email = email;
		this.motDePasse = motDePasse;
	}

	public Membre(long id, String nom, String prenom, String email, String motDePasse, boolean admin) {
		super(id, nom, prenom);
		this.email = email;
		this.motDePasse = motDePasse;
		this.admin = admin;
	}
	
	



}
