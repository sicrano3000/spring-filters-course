package com.spring.filter.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.filter.course.domain.Category;
import com.spring.filter.course.model.FilterModel;
import com.spring.filter.course.model.PageModel;
import com.spring.filter.course.repository.CategoryRepository;

@Service
public class CategoryService implements IListService<Category> {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> list() {
		var categories = categoryRepository.findAll();
		
		return categories;
	}

	@Override
	public PageModel<Category> list(FilterModel filter) {			
		var categoryPage = categoryRepository.findAll(filter.toSpringPageable());		
		var pageModel = new PageModel<>(categoryPage);
		
		return pageModel;
	}

}
