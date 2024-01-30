package blogdev.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogdev.bo.Article;
import blogdev.dal.ArticleDao;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public Article getById(long id) {		
		return articleDao.findById(id);
	}

	@Override
	public void addArticle(Article article) {
		articleDao.insert(article);
	}

	@Override
	public List<Article> getAll() {
		return articleDao.findAll();
	}

}
