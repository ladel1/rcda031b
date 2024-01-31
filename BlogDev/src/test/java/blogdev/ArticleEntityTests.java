package blogdev;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import blogdev.bo.Article;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@SpringBootTest
public class ArticleEntityTests {

	private Validator validator;
	
	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	void testArticleValidation() {
		// Arrange
		Article article = new Article(); 
		article.setTitre("aaa");
		article.setAuteur("aa");
		assertThat(validator.validate(article)).isEmpty();
	}
	
}
