package fr.eni.tp.filmotheque.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Classe de configuration de Spring Security
 * C'est ici qu'on va définir les beans de configuration, 
 * notamment celui qui spécifie les routes/ressources accessibles aux utilisateurs connectés / non connectés
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean // on définit un bean qui sera présent dans le contexte Spring => Spring Security va se baser la dessus pour configurer son filtre de securité
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(authorize) -> authorize
				
				/*
				 * .requestMatchers() : permet de définir un ensemble d'url selon un pattern qui peut être
				 * - ("url1", "url2",...)
				 * - ("/debutUrl/*)
				 * 
				 * On va pouvoir ensuite appliquer une contrainte d'accès à cet ensemble :
				 * - .authenticated() : pour accéder à une de ces urls il faut être connecté
				 * - .hasRole("xxx") : pour accéder à une de ces urls il faut être connecté avec un utilisateur qui possède le rôle "xxx"
				 * - .permitAll() : tout le monde (même les utilisateurs non connectés) peut accéder à ces pages
				 * 
				 *  FAIRE ATTENTION A L'ORDRE DES MATCHERS : commencer par le plus restrictif 
				 *  car si on met en premier .requestMatchers("/*").permitAll() : toutes les requêtes seront autorisées
				 */
				// la page qui est à l'url /film/creer va nécessiter d'être connecte avec un utilisateur qui possède le rôle admin (.hasRole("admin"))
				.requestMatchers("/film/creer").hasRole("admin")
				// la page qui est à l'url /films/{id}/avis va nécessiter d'être connecte (.authenticated())
				.requestMatchers("/films/*/avis").authenticated()
				// toutes les autres pages sont accessibles à tout le monde (même les utilisateurs non connectés)
				.requestMatchers("/**").permitAll())
				// J'utilise une authentification basique (login/mot de passe) 
				.httpBasic(Customizer.withDefaults())
				// J'utilise le formulaire par défaut proposé par Spring Security (possible de customiser)
				.formLogin(Customizer.withDefaults())
				// Après déconexion, je redirige sur la page d'accueil (/) 
				.logout((logout) -> logout.logoutSuccessUrl("/"));
		return http.build();
	}
	
	
	@Bean // on définit un bean pour l'utilitaire d'encryption de mot de passe
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	/*
	 * @Bean : on commente l'instantiation du Bean car on va gérer les utilisateurs de façon personalisé
	 * en créant un Service qui implémente UserDetailsService 
	  
	// on définit un bean pour la gestion des utilisateurs en mémoire 
	public InMemoryUserDetailsManager userDetailsService() {
		// on définit une liste d'utilisateurs qu'on renverra qui correspondra à ceux utilisables pour la connexion
		List<UserDetails> userDetailsList = new ArrayList<>();
		
		// on ajoute un utilisateur avec username : membre, mdp: membre123, role : user
		userDetailsList
				.add(User.withUsername("membre").password(passwordEncoder().encode("membre123"))
				.roles("user").build());
		// on ajoute un utilisateur avec username : admin, mdp: admin123, role : user, admin (comme on a le rôle admin on va pouvoir accéder à /pageAdmin) 
		userDetailsList
				.add(User.withUsername("admin").password(passwordEncoder().encode("admin123"))
				.roles("admin", "user").build());
		
		return new InMemoryUserDetailsManager(userDetailsList);
	 
	}*/
}