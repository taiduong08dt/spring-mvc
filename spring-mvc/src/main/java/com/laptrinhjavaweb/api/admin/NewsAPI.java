package com.laptrinhjavaweb.api.admin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.NewsDTO;

@RestController(value = "newsAPIOfAdmin")
public class NewsAPI {
	
	@PostMapping("/api/news")
	public NewsDTO createNews(@RequestBody NewsDTO newsDTO) {
		return newsDTO;
	}
	
	@PutMapping("/api/news")
	public NewsDTO updateNews(@RequestBody NewsDTO newsDTO) {
		return newsDTO;
	}
	
	@DeleteMapping("/api/news")
	public void deleteNews(@RequestBody long[] ids) {
		System.out.println("ok");
	}
}
