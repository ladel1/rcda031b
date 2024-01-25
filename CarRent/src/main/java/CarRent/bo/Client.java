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
@Table(name="clients")
@Data
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 30)
	private String prenom;
	@Column(length = 30)
	private String nom;
	
	@DateTimeFormat(pattern="YYYY-MM-dd")
	private LocalDate dateDeNaissance;
	
	@Column(length = 15)
	private String numPermis;
	@Column(length = 100)
	private String adresse;
	@Column(length = 10)
	private String tel;
	
	@OneToMany(mappedBy = "client")
	private List<Location> locations;
	
}
