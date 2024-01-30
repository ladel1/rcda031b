package blogdev.bll;

import java.util.List;

import blogdev.bo.Article;

public interface ArticleService {
	Article getById(long id);
	void addArticle(Article article);
	List<Article> getAll();
}
