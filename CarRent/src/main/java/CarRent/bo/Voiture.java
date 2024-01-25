package CarRent.bo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "voitures")
@Data
public class Voiture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50)	
	private String marque;
	@Column(length = 50)
	private String modele;
	private int vitesseMax;
	@Column(length = 7)
	private String matricule;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateMiseService;
	
	private float tarifDeBase;
	
	@Column(length = 10)
	private String couleur;
	
	@OneToMany(mappedBy = "voiture")
	private List<Location> locations;
	
}
