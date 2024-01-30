package ebank.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@DiscriminatorValue("con")
@Table(name="conseillers")
@Data
public class Conseiller extends Personne {
	@ToString.Exclude
	@OneToMany(mappedBy = "conseiller",fetch = FetchType.EAGER)
	private List<Client> clients = new ArrayList<Client>();
	
	@ToString.Exclude
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
}
