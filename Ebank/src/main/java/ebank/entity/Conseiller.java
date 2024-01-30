package ebank.entity;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@DiscriminatorValue("con")
@Table(name="conseillers")
@Data
public class Conseiller extends Personne {

	@OneToMany(mappedBy = "conseiller")
	private List<Client> clients ; /// !!!!!!!!!!!!	
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
}
