package blogdev;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import blogdev.bll.ArticleService;
import blogdev.bo.Article;

@SpringBootTest
public class BlogServiceTests {

	@Autowired
	private ArticleService articleService;
	
	@Test
	public void testFindAllArticleService() {
		//Arrage
		int articleSize = 10;
		// act
		List<Article> articles = articleService.getAll();
		//System.err.println(articles);
		// Assert
		assertNotNull(articles);
		assertEquals(articleSize, articles.size());
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
	
}
