package blogdev.dal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.github.javafaker.Faker;

import blogdev.bo.Article;
import blogdev.bo.Category;

//@Repository
//@Primary
public class ArticleRepositoryMock implements ArticleDao {

	private List<Article> articles ;
	
	private Faker faker;
	
	public ArticleRepositoryMock(Faker faker) {
		this.faker = faker;
		this.articles = new ArrayList<Article>();
		
		for (int i = 0; i < 10; i++) {
			long id = Long.parseLong(faker.number().numberBetween(1,999)+"");
			Category category = new Category(id, faker.book().genre());
			
			long idArticle = (long)i+1;
			Article article =new Article(idArticle, 
					faker.book().title(), 
					faker.lorem().paragraph(4), 
					faker.book().author(),
					category
					);
			
			articles.add(article);
		}
	}
	
	@Override
	public Article findById(long pk) {
		return articles
				.stream()
				.filter( a -> a.getId() == pk )
				.findFirst()
				.orElse(null);
//		for (Article article : articles) {
//			if(article.getId()==pk) return article;
//		}
//		return null;
	}

	@Override
	public void insert(Article article) {
		articles.add(article);
	}

	@Override
	public List<Article> findAll() {
		return articles;
	}

	@Override
	public Article findByTitre(String titre) {
		// TODO Auto-generated method stub
		return null;
	}

}
