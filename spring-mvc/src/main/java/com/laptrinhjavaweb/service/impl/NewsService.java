package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.NewsConverter;
import com.laptrinhjavaweb.dto.NewsDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewsEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewsRepository;
import com.laptrinhjavaweb.service.INewsService;

@Service
public class NewsService implements INewsService{

	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private NewsConverter newsConverter;
	
	@Override
	public List<NewsDTO> findAll(Pageable pageable) {
		List<NewsDTO> models = new ArrayList<NewsDTO>();
		List<NewsEntity> entities = newsRepository.findAll(pageable).getContent();
		for(NewsEntity item : entities) {
			NewsDTO newsDTO = newsConverter.toDto(item);
			models.add(newsDTO);
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return (int) newsRepository.count();
	}

	@Override
	public NewsDTO findById(long id) {
		NewsDTO newsDto = newsConverter.toDto(newsRepository.findOne(id));
		return newsDto;
	}


	@Override
	@Transactional
	public NewsDTO save(NewsDTO dto) {
		CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode());
		NewsEntity newsEntity = new NewsEntity();
		if(dto.getId() != null) {
			NewsEntity oldNews = newsRepository.findOne(dto.getId());
			oldNews.setCategory(category);
			newsEntity = newsConverter.toEntity(dto, oldNews);
		} else {
			newsEntity = newsConverter.toEntity(dto);
			newsEntity.setCategory(category);
		}
		return newsConverter.toDto(newsRepository.save(newsEntity));
	}

}
