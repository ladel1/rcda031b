package blogdev.bo;

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
	private String titre;
	@NonNull
	private String contenu;
	@NonNull
	private String auteur;
	
	private Category category;
	
	public String getTitre() {
		return titre.toUpperCase();
	}
	
}
