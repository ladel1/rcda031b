package blogdev.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import blogdev.bo.Article;
import blogdev.bo.Category;

@Repository
public class ArticleRepository implements ArticleDao {

	private final static String INSERT = "INSERT articles(titre,contenu,auteur,category_id) "
			+ "VALUES (:titre,:contenu,:auteur,:category)";
	private final static String SELECT_BY_ID = "SELECT * FROM articles WHERE id = :id";
	private final static String SELECT_BY_ID_EAGER = "SELECT a.*, c.nom FROM articles a "
			+ "INNER JOIN categories c ON a.category_id = c.id WHERE a.id = :id";
	private final static String SELECT_ALL = "SELECT * FROM articles";
	private final static String SELECT_ALL_EAGER = "SELECT a.*, c.nom FROM articles a "
			+ "INNER JOIN categories c ON a.category_id = c.id";
	
	@Autowired
	private NamedParameterJdbcTemplate npjt;
	
	@Override
	public Article findById(long pk) {
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id",pk);	
// FETCH LAZY		
//		return npjt.queryForObject(
//				SELECT_BY_ID, 
//				param, 
//				// LAZY FETCH CUSTOM 
//				new ArticleMapper()
//				);
		
		
		// FETCH EAGER
		return npjt.queryForObject(
				SELECT_BY_ID_EAGER, 
				param, 
				// LAZY FETCH CUSTOM 
				new ArticleMapper()
				);
	}

	@Override
	public void insert(Article article) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("titre", article.getTitre());
		param.addValue("contenu", article.getContenu());
		param.addValue("auteur", article.getAuteur());
		param.addValue("category", article.getCategory().getId());
		KeyHolder key = new GeneratedKeyHolder();
		int num = npjt.update(INSERT, param,key);
		if(num>0) article.setId( key.getKey().longValue() );
	}

	@Override
	public List<Article> findAll() {		
		return npjt.query(SELECT_ALL_EAGER, new ArticleMapper());		
		//return npjt.query("EXEC articles_liste",new ArticleMapper());
	}
	
	class ArticleMapper implements RowMapper<Article>{

		@Override
		public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
			// Lazy Fetch
//			Category category = new Category();
//			category.setId(rs.getLong("category_id"));
//			Article article = new Article(
//					rs.getLong("id"), 
//					rs.getString("titre"), 
//					rs.getString("contenu"), 
//					rs.getString("auteur"),
//					category
//					);	
			// EAGER FATCH
			Category category = new Category(rs.getLong("category_id"),rs.getString("nom"));
			Article article = new Article(
					rs.getLong("id"), 
					rs.getString("titre"), 
					rs.getString("contenu"), 
					rs.getString("auteur"),
					category
					);				
			return article;
			
		}
		
	}

	
}
