package com.laptrinhjavaweb.controller.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.NewsDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.util.MessageUtil;

@Controller(value = "newsControllerOfAdmin")
public class NewsController {
	@Autowired
	private INewsService newsService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam(value = "message", required = false) String message) {
		NewsDTO model = new NewsDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/news/list");
		
		if(message != null) {
			Map<String, String> mes = messageUtil.getMessage(message);
			mav.addObject("message", mes.get("message"));
			mav.addObject("alert", mes.get("alert"));
		}
		
		Pageable pageble = new PageRequest(page -1, limit);
		model.setListResult(newsService.findAll(pageble));
		model.setTotalItem(newsService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editNews(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "message", required = false) String message) {
		ModelAndView mav = new ModelAndView("admin/news/edit");
		NewsDTO model = new NewsDTO();
		Map<String, String> categories ;
		if(id != null) {
			model = newsService.findById(id);
		}
		if(message != null) {
			Map<String, String> mes = messageUtil.getMessage(message);
			mav.addObject("message", mes.get("message"));
			mav.addObject("alert", mes.get("alert"));
		}
		categories = categoryService.findAll();
		mav.addObject("categories", categories);
		mav.addObject("model", model);
		return mav;
	}
}
