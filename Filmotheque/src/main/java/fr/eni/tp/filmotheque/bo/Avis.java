package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // génère un constructeur par défaut
@AllArgsConstructor // génère un constructeur avec tous les arguments
@Data
/*
 * @Data fait tout ca : 
 * @Getter @Setter // génère tous les getters/setters
 * @ToString // génère un toString
 * @EqualsAndHashCode // génère un equals basé sur l'égalité des attributs
 */
@Entity
@Table(name = "AVIS")
public class Avis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id;
	
	private int note;
	
	@Column(nullable = false,length = 255)
	private String commentaire;
	
	/*
	 * associations traduites en attribut
	 */
	@ManyToOne
	@JoinColumn(name="id_membre")
	private Membre membre; // un avis est associé à un membre
	
	@ManyToOne
	@JoinColumn(name="id_film")
	private Film film;
	
	/*
	 * Pour les constructeurs qui ont des arguments mais pas TOUS, Lombok ne sait pas faire
	 * => il faut faire manuellement
	 */
	public Avis(int note, String commentaire) {
		super();
		this.note = note;
		this.commentaire = commentaire;
	}
}
