package com.eni.jeuxvideo.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Erreur: Le champs email est obligatoire!")
	@Email(message = "Erreur: faut mettre un email!")		
	@Column(length = 320,nullable = false,unique = true)
	private String email;
	
	@NotBlank(message="Erreur: Le champs Username est obligatoire!")
	@Length(
			min= 3,
			max= 20,
			message = "Erreur: Le champs Username doit être entre 3 et 20 caractères!"
			)	
	@Column(length = 20,nullable = false,unique = true)
	private String username;
	
	private List<Role> roles;	
	
	@NotBlank(message="Erreur: Le champs Password est obligatoire!")
	@Column(length = 256,nullable = false)
	private String password;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> gtantedListe = new ArrayList<GrantedAuthority>();		
		for (Role role : roles) {
			gtantedListe.add( new SimpleGrantedAuthority(role.name()));
		}				
		return gtantedListe;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
