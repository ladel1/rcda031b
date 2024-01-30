package ebank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@Table(name="personnes")
public class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 30)
	private String nom;
	
	@Column(length = 30)
	private String prenom;
	
	private int age;
	
	private String adresse;
	@Column(length = 5)
	private String cp;
	@Column(length = 30)
	private String ville;
	@Column(length = 10)
	private String tel;
	
}
