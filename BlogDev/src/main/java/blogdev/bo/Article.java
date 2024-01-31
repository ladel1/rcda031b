package blogdev.bo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Article {

	@NonNull
	private Long id;
	@NonNull
	@NotBlank(message = "Le champs Titre ne peut être vide!")
	@Length(
			min = 3,
			max = 200,
			message = "Le titre doit contenir entre 3 et 200 caractères!"
			)
	private String titre;
	@NonNull
	private String contenu;
	
	@NonNull
	@Length(
			min = 2,
			max = 30,
			message = "L'auteur doit contenir entre 2 et 30 caractères!"
			)	
	@NotBlank(message = "Le champs Auteur ne peut être vide!")
	private String auteur;
	
	private Category category;
	
	public String getTitre() {
		return titre.toUpperCase();
	}
	
}
