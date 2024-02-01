package fr.eni.tp.filmotheque.bo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "FILM")
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 250,nullable = false)
	private String titre;
	
	@Column(length = 4,nullable = false)
	private int annee;
	
	@Column(nullable = false)
	private int duree;
	
	@Column(length = 250,nullable = false)
	private String synopsis;
	
	/*
	 * associations traduites en attribut
	 */
	@ManyToOne
	@JoinColumn(name = "id_genre")
	private Genre genre; // un film est associé à un genre 
	
	@OneToMany(mappedBy = "film")
	private List<Avis> avis = new ArrayList<>();  // un film est associé à plusieurs (*) avis => on traduit par une liste d'avis
//	
	@ManyToOne
	@JoinColumn(name="id_realisateur")
	private Participant realisateur; // un film est associé à un réalisateur
	
	@ManyToMany
	@JoinTable(
			name = "ACTEURS"
			)
	private List<Participant> acteurs = new ArrayList<>(); // un film est associé à plusieurs (*) acteurs => on traduit par une liste de participants
	
	/*
	 * Pour les constructeurs qui ont des arguments mais pas TOUS, Lombok ne sait pas faire
	 * => il faut faire manuellement
	 */
	public Film(String titre, int annee, int duree, String synopsis) {
		super();
		this.titre = titre;
		this.annee = annee;
		this.duree = duree;
		this.synopsis = synopsis;
	}
	public Film(long id, String titre, int annee, int duree, String synopsis) {
		super();
		this.id = id;
		this.titre = titre;
		this.annee = annee;
		this.duree = duree;
		this.synopsis = synopsis;
	}
}
