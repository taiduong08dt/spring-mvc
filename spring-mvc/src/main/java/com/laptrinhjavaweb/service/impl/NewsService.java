package com.laptrinhjavaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.service.INewsService;

@Service
public class NewsService implements INewsService{

	@Autowired
	private INewsDAO newsDao;
	
	@Override
	public List<NewsModel> findAll() {
		// TODO Auto-generated method stub
		return newsDao.findAll();
	}
}
