package com.eni.jeuxvideo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eni.jeuxvideo.bll.GameService;
import com.eni.jeuxvideo.bo.Game;

@Controller
public class SearchController {

	@Autowired
	private GameService gameService;
	
	@GetMapping("/search")
	public String search(@RequestParam(name = "q") String query,Model model  ) {
		
		List<Game> games = gameService.searchGames(query);
		model.addAttribute("games", games);
		return "jeux/search";
	}
	
}
