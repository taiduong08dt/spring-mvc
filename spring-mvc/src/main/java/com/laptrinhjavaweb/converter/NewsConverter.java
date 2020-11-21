package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.NewsDTO;
import com.laptrinhjavaweb.entity.NewsEntity;

@Component
public class NewsConverter {
	public NewsDTO toDto(NewsEntity entity) {
		NewsDTO result = new NewsDTO();
		result.setId(entity.getId());
		result.setTitle(entity.getTitle());
		result.setShortDescription(entity.getShortDescription());
		result.setContent(entity.getContent());
		result.setThumbnail(entity.getThumbnail());
		result.setCategoryCode(entity.getCategory().getCode());
		return result;
	}
	
	public NewsEntity toEntity(NewsDTO dto) {
		NewsEntity result = new NewsEntity();
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setContent(dto.getContent());
		result.setThumbnail(dto.getThumbnail());
		return result;
	}
	
	public NewsEntity toEntity(NewsDTO dto, NewsEntity entity) {
		entity.setTitle(dto.getTitle());
		entity.setShortDescription(dto.getShortDescription());
		entity.setContent(dto.getContent());
		entity.setThumbnail(dto.getThumbnail());
		return entity;
	}
}
