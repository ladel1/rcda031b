package blogdev.dal;

import java.util.List;

import blogdev.bo.Article;

public interface ArticleDao {

	Article findById(long pk);
	void insert(Article article);
	List<Article> findAll();
}
