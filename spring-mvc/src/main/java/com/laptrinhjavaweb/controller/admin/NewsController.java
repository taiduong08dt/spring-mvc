package com.laptrinhjavaweb.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewsDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewsService;

@Controller(value = "newsControllerOfAdmin")
public class NewsController {
	@Autowired
	private INewsService newsService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		NewsDTO model = new NewsDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/news/list");
		Pageable pageble = new PageRequest(page -1, limit);
		model.setListResult(newsService.findAll(pageble));
		model.setTotalItem(newsService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editNews(@RequestParam(value = "id", required = false) Long id) {
		ModelAndView mav = new ModelAndView("admin/news/edit");
		NewsDTO model = new NewsDTO();
		Map<String, String> categories ;
		if(id != null) {
			model = newsService.findById(id);
		}
		categories = categoryService.findAll();
		mav.addObject("categories", categories);
		mav.addObject("model", model);
		return mav;
	}
}
