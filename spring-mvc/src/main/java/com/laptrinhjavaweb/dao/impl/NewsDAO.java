package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.mapper.NewsMapper;
import com.laptrinhjavaweb.model.NewsModel;

@Repository
public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {

	@Override
	public List<NewsModel> findAll() {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		return query(sql.toString(), new NewsMapper());
	}

}
