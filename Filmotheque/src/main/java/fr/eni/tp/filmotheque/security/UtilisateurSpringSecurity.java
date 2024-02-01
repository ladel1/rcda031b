package fr.eni.tp.filmotheque.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.eni.tp.filmotheque.bo.Membre;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class UtilisateurSpringSecurity
 * C'est une classe qui implémente UserDetails et qui doit redefinir les méthodes 
 * qui permettent à Spring Security de réaliser ses diffférentes opérations :
 * - getPassword() 
 * - getUsername()
 * - etc...
 * 
 *  On lui mets en attribut un objet de type Membre afin de pouvoir récupérer une instance de Membre à partir de l'utilisateur connecté
 */
@Data
@AllArgsConstructor
public class UtilisateurSpringSecurity implements UserDetails {
	/*
	 * Je mets un attribut membre de façon à pouvoir accéder au membre depuis mon utilisateur connecté
	 */
	private Membre membre;

	/**
	 * Comment on détermine les droits de l'utilisateur ?
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/*
		 * Il y a une nuance entre les permissions (authorities) et les rôles
		 * Un rôle est une permission avec le préfixe : "ROLE_"
		 * permission ROLE_xxx = rôle xxx
		 */
		
		// à partir du membre, si il est admin => on lui ajoute le droit "ROLE_admin"
		if (membre.isAdmin()) {
			return List.of(new SimpleGrantedAuthority("ROLE_admin"));
		}
		// Dans tous les cas, on lui ajoute le droit "ROLE_user"
		return List.of(new SimpleGrantedAuthority("ROLE_user"));
	}

	/**
	 * Comment on détermine le mot de passe ?
	 */
	@Override
	public String getPassword() {
		// à partir du membre
		return membre.getMotDePasse();
	}

	/**
	 * Comment on détermine le nom d'utilisateur ?
	 */
	@Override
	public String getUsername() {
		// à partir du membre
		return membre.getEmail();
	}

	/**
	 * Comment on détermine si le compte n'est pas expiré ?
	 */
	@Override
	public boolean isAccountNonExpired() {
		// toujours vrai
		return true;
	}

	/**
	 * Comment on détermine si le compte n'est pas bloqué ?
	 */
	@Override
	public boolean isAccountNonLocked() {
		// toujours vrai
		return true;
	}

	/**
	 * Comment on détermine si le mot de passe n'a pas expiré
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// toujours vrai
		return true;
	}

	/**
	 * Comment on détermine si l'utilisateur est actif
	 */
	@Override
	public boolean isEnabled() {
		// toujours vrai
		return true;
	}
}