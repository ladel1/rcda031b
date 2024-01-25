package CarRent.bo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "locations")
@Data
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern="YYYY-MM-dd")
	private LocalDate dateDebut;
	
	@DateTimeFormat(pattern="YYYY-MM-dd")
	private LocalDate dateRetour;
	
	@ManyToOne
	private Voiture voiture;
	
	@ManyToOne
	private Client client;
	
}
