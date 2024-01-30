package blogdev.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import blogdev.bo.Article;

@Repository
public class ArticleRepository implements ArticleDao {

	private final static String INSERT = "INSERT articles(titre,contenu,auteur) VALUES (:titre,:contenu,:auteur)";
	private final static String SELECT_BY_ID = "SELECT * FROM articles WHERE id = :id";
	private final static String SELECT_ALL = "SELECT * FROM articles";
	
	@Autowired
	private NamedParameterJdbcTemplate npjt;
	
	@Override
	public Article findById(long pk) {
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id",pk);		
		return npjt.queryForObject(
				SELECT_BY_ID, 
				param, 
				new BeanPropertyRowMapper<>(Article.class)
				);
	}

	@Override
	public void insert(Article article) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("titre", article.getTitre());
		param.addValue("contenu", article.getContenu());
		param.addValue("auteur", article.getAuteur());
		KeyHolder key = new GeneratedKeyHolder();
		int num = npjt.update(INSERT, param,key);
		if(num>0) article.setId( key.getKey().longValue() );
	}

	@Override
	public List<Article> findAll() {		
		//return npjt.query(SELECT_ALL, new BeanPropertyRowMapper(Article.class));
		
		return npjt.query("EXEC articles_liste", new BeanPropertyRowMapper(Article.class));
	}

	
}
