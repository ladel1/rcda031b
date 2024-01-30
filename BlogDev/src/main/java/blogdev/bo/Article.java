package blogdev.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

	private Long id;
	
	private String titre;
	
	private String contenu;
	
	private String auteur;
	
}
