package com.laptrinhjavaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.service.ICategoryService;

public class CategoryService implements ICategoryService{
	@Autowired
	private ICategoryDAO categoryDao;
	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

}
