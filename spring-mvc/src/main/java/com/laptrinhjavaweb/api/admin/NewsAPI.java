package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.NewsDTO;
import com.laptrinhjavaweb.service.INewsService;

@RestController(value = "newsAPIOfAdmin")
public class NewsAPI {
	@Autowired
	private INewsService newsService;
	
	@PostMapping("/api/news")
	public NewsDTO createNews(@RequestBody NewsDTO newsDTO) {
		return newsService.save(newsDTO);
	}
	
	@PutMapping("/api/news")
	public NewsDTO updateNews(@RequestBody NewsDTO newsDTO) {
		return newsService.save(newsDTO);
	}
	
	@DeleteMapping("/api/news")
	public void deleteNews(@RequestBody long[] ids) {
		newsService.delete(ids);
	}
}
