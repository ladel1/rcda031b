package blogdev;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.github.javafaker.Faker;

import blogdev.bo.Article;
import blogdev.dal.ArticleDao;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private Faker faker;
	
	@Autowired
	private ArticleDao articleDao;

	void testFindByTitre() {
		// Arrange
		String inputTitre = "angu";
		// Act
		Article article = articleDao.findByTitre(inputTitre);
		System.err.println(article);
		// assert
		assertThat(article).isNotNull();		
	}
	
	@Test
	void testAjouterArticle() throws Exception {
		// Arrange
		String inputTitre = faker.book().title();
		String inputContenu = faker.lorem().paragraph(2);
		String inputAuteur = faker.book().author();
		Random rs  = new Random();
		long category_id = rs.nextLong(1,4);
		String path = "/articles/ajouter";
		// Act
		mockMvc.perform( 
				MockMvcRequestBuilders.post(path)
				                      .contentType(
				                    		  MediaType.APPLICATION_FORM_URLENCODED
				                    		  )
				                      .param("titre", inputTitre)
				                      .param("contenu", inputContenu)
				                      .param("auteur", inputAuteur)
				                      .param("category.id", category_id+"")				                      				
				).andExpect(
						MockMvcResultMatchers.status().is3xxRedirection()
						);
		
		Article article  = articleDao.findByTitre(inputTitre);
		System.err.println(article);
		// Assert
		assertThat(article).isNotNull();
		
		
	}
	
}
