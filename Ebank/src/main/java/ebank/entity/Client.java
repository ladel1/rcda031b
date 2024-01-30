package ebank.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@DiscriminatorValue("cli")
@Table(name="clients")
@Data
public class Client extends Personne {
	
	@ToString.Exclude
	@OneToOne(mappedBy = "client")
	private Compte compte;
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name="conseiller_id")
	private Conseiller conseiller;
	
	
	@ToString.Exclude
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
}
