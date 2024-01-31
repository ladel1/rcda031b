package blogdev;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.javafaker.Faker;

import blogdev.bll.ArticleService;
import blogdev.bo.Article;
import blogdev.bo.Category;

@SpringBootTest
public class BlogServiceTests {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private Faker faker;
	
	@Test
	public void testFindAllArticleService() {
		//Arrage
		int articleSize = 10;
		// act
		List<Article> articles = articleService.getAll();
		//System.err.println(articles);
		// Assert
		assertNotNull(articles);
		//assertEquals(articleSize, articles.size());
		assertThat(articles).hasSize(10);
	}
	
	@Test
	public void testGetArticleById() {
		// Arrage
		long inputId = 15;
		// act
		Article article = articleService.getById(inputId);
		System.err.println(article);
		//assert
		assertNull(article);
	}
	
	@Test
	void testAddArticle() {
		
		// Arrange		
		long id = Long.parseLong(faker.number().numberBetween(1,999)+"");
		Category category = new Category(id, faker.book().genre());
		
		long idArticle = 11;
		Article article =new Article(idArticle, 
				faker.book().title(), 
				faker.lorem().paragraph(4), 
				faker.book().author(),
				category
				);
		// Act		
		articleService.addArticle(article);
		Article actualArticle = articleService.getById(idArticle);
		// Assert
		assertNotNull(actualArticle);
		assertEquals(article, actualArticle);
	}
	
}
