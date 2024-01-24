package com.eni.jeuxvideo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eni.jeuxvideo.bll.CategoryService;
import com.eni.jeuxvideo.bo.Category;
import com.eni.jeuxvideo.bo.Game;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/ajouter")
	public String afficherFormulaire(Model model) {
		model.addAttribute("category", new Category());
		return "category/ajouter";
	}
	
	
	@PostMapping("/ajouter")
	public String ajouter( 
			@Valid @ModelAttribute Category category,
			BindingResult br
			) {
		if( br.hasErrors() ) return "jeux/ajouter";		
		categoryService.ajouterCategory(category);		
		//redirection vers la liste de jeux
		return "redirect:/jeux/ajouter";
	}

}
