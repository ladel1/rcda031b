package fr.eni.tp.filmotheque.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Participant;
import lombok.AllArgsConstructor;

/**
 * Converter
 * Spécifie COMMENT on passe d'une donnée au format String (l'id du participant passé en paramètre de la requête HTTP)
 * à un Genre
 */
@Component // permet de rajouter le converter au contexte Spring afin qu'il l'utilise AUTOMATIQUEMENT
@AllArgsConstructor // Le fait de créer un constructeur avec un argument effectue un @Autowire automatique sur filmService
public class ParticipantConverter implements Converter<String, Participant>{

	/*
	 * On référencer le service dans un attribut
	 */
	private FilmService filmService;
	
	@Override
	public Participant convert(String idFormatString) {
		// on est obligé de convertir l'id qui nous arrive au format texte depuis la requête HTTP
		Long idFormatLong = Long.parseLong(idFormatString);
		
		// le converter renvoie le Genre correspondant à l'id
		return filmService.consulterParticipantParId(idFormatLong);
	}

}
