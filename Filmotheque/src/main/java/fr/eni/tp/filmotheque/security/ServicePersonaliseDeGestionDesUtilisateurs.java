package fr.eni.tp.filmotheque.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.dal.MembreRepository;

/**
 * En créant un Service annoté @Service (donc dans le contexte Spring)
 * qui implémente UserDetailsService
 * Spring va comprendre qu'il doit utiliser ce service pour la gestion des utilisateurs
 * 
 * => on va devoir lui préciser comment on recupère un utilisateur à partir d'un username
 */
@Service
public class ServicePersonaliseDeGestionDesUtilisateurs implements UserDetailsService {
	//List<Membre> membres = new ArrayList<>();
	@Autowired
	private MembreRepository membreRepository;
	/**
	 * Comme on a pas encore de base de données, on crée une liste en mémoire pour gérer nos utilisateurs
	 * J'ai injecté le bean passwordEncoder défini dans SecurityConfiguration car le 
	 * qui me permet d'encoder le mot de passe
	 */
//	public ServicePersonaliseDeGestionDesUtilisateurs(PasswordEncoder passwordEncoder) {
//		Membre m1 = new Membre(1, "Latibi", "Adel", "adel", passwordEncoder.encode("adel"), false);
//		Membre m2 = new Membre(2, "Mace", "Cyril", "cyril", passwordEncoder.encode("cyril"), false);
//		Membre m3 = new Membre(3, "Pouce", "Tom","membre2", passwordEncoder.encode("membre2"), false);
//		Membre m4 = new Membre(4, "Norris","Chuck","admin", passwordEncoder.encode("admin"), true);
//		membres.add(m1);
//		membres.add(m2);
//		membres.add(m3);
//	}

	/**
	 * On doit specifier à Spring "comment on récupère les utilisateurs"
	 * à partir d'un username rentré dans le formulaire de Login
	 * A partir de ça, Spring Boot se débrouile pour comparer 
	 *  - le mot de passe envoyé avec celui qui est retourné par .getPassword()
	 *  - les rôles envoyés avec ceux qui sont nécessaires pour accéder à la page  
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		System.err.println("***Username***");
		/**
		 * Comme on a pas encore de base de données, 
		 * on va chercher l'utilisateur dans notre liste en mémoire
		 * 
		 * plus tard, on remplacera pour effectuer une recherche sur la base de donéne plutôt
		 */
//		for (Membre membre : membres) {
//			if (membre.getEmail().equals(username)) {
//				// la méthode ne doit pas renvoyer de Membre mais une instance de UserDetails
//				return new UtilisateurSpringSecurity(membre);
//			}
//		}
		// si le membre n'est pas trouvé, on doit renvoyé une exception qui sera catchée par Spring Boot (pour afficher le messsage d'erreur dans le formulaire de login)
		Membre membre = membreRepository.findByEmail(username);
		if(membre == null) throw new UsernameNotFoundException(username);
		return new UtilisateurSpringSecurity(membre);
	}
}