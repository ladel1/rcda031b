package blogdev.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import blogdev.bll.ArticleService;
import blogdev.bo.Article;

@Component
public class StringToArticleConverter implements Converter<String, Article> {

	@Autowired
	private ArticleService articleService;
	
	@Override
	public Article convert(String source) {
		System.err.println("****** Start Converter Article *****");
		return articleService.getById(Long.parseLong(source));
	}

}
