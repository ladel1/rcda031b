package blogdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import blogdev.bll.ArticleService;
import blogdev.bo.Article;

@Controller
@RequestMapping("/articles")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("articles",articleService.getAll());
		return "article/list";
	}
	
	@GetMapping("/detail/{id:[0-9]+}")
	public String detail(Model model,@PathVariable("id") long id) {
		model.addAttribute("article",articleService.getById(id));
		return "article/detail";
	}
	
	@GetMapping("/ajouter")
	public String articleForm(Model model) {
		Article article = new Article();
		article.setId(1L);
		model.addAttribute("article", article);
		return "article/ajouter";
	}
	
	@PostMapping("/ajouter")
	public String persistArticleForm(@ModelAttribute Article article) {
		articleService.addArticle(article);
		return "redirect:/articles/detail/%d".formatted(article.getId());
	}
	
}
