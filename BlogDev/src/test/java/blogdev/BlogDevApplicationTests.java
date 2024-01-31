package blogdev;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import blogdev.bo.Article;
import blogdev.bo.Category;
import blogdev.dal.ArticleDao;
import blogdev.dal.ArticleRepository;

@SpringBootTest
class BlogDevApplicationTests {

	@Autowired
	private ArticleDao articleDao;
	
	@Test
	void contextLoads() {
	}

	@Test
	void testEntityArticle() {
		long inputId = 15L;
		String inputTitre = "Formation Symfony";
		String inputContenu = "balbalab";
		String inputAuteur = "adel";
		Article article = new Article(inputId, inputTitre, inputContenu, inputAuteur);		
		assertNotNull(article);	
		
		String expectedTitre = "FORMATION SYMFONY";
		assertEquals(expectedTitre, article.getTitre());
		
	}
	
	@Test
	void testArticleDaoRespository() {
		// Arrange
		long inputId = 1;
		Article excpectedArticle = new Article(1L,
				"Tuto Spring BOOT",
				"blabalbalabalb",
				"adel");
		// Act
		Article actualArticle = articleDao.findById(inputId);
		// Assert
		assertNotNull(actualArticle);
		assertEquals(excpectedArticle.getAuteur(), actualArticle.getAuteur());
		assertEquals(excpectedArticle.getTitre(), actualArticle.getTitre());
		assertEquals(excpectedArticle.getContenu(), actualArticle.getContenu());
		assertEquals(excpectedArticle.getId(), actualArticle.getId());
	}
	
	@Test
	void testArticleCategoryDaoRespository() {
		// Arrange
		long inputId = 1;
		long inputIdCategory = 2;
		Category category = new Category(inputIdCategory, "Framework");
		
		Article excpectedArticle = new Article(1L,
				"Tuto Spring BOOT",
				"blabalbalabalb",
				"adel",
				category
				);
		// Act
		Article actualArticle = articleDao.findById(inputId);
		Category actualCategory = actualArticle.getCategory();
		// Assert
		assertNotNull(actualArticle);
		assertNotNull(actualCategory);
		assertEquals(inputIdCategory, actualCategory.getId());
		assertEquals("Framework", actualCategory.getNom());
	}
	
	
	//@Test
	void testFindAllArticles() {
		// arrange
		int expectedCount = 6;
		// act
		List<Article> articles = articleDao.findAll();
		// assert
		assertNotNull(articles);
		assertEquals(7, articles.size());		
	}
	
	@Test
	void testAjoutArticle() {
		
		// Arrage
		String titre = "Tuto NodeJs & ExpressJS";
		String contenu = "ExpressJS balbalablabalba";
		String auteur = "Adel";
		long category_id = 2;		
		Category category = new Category();
		category.setId(category_id);
		Article article = new Article(0L, titre, contenu, auteur, category);
		// Act		
		articleDao.insert(article);
		// Assert
		assertNotNull(article.getId());				
	}
	
	
}
