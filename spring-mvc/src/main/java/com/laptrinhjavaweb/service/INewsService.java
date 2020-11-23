package com.laptrinhjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.NewsDTO;

public interface INewsService {
	List<NewsDTO> findAll(Pageable pageable);
	int getTotalItem();
	NewsDTO findById(long id);
	NewsDTO save(NewsDTO dto);
	void delete(long[] ids);
}
