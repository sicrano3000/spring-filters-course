package com.spring.filter.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.spring.filter.course.domain.Category;
import com.spring.filter.course.model.FilterModel;
import com.spring.filter.course.model.PageModel;
import com.spring.filter.course.repository.CategoryRepository;
import com.spring.filter.course.specification.CategorySpecification;

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
		Specification<Category> spec = null;
		var pageable = filter.toSpringPageable();	
		var equalFilters = filter.getEqualFilters(); 
		
		if (!equalFilters.isEmpty()) {
			var firstEqualFilters = equalFilters.get(0);
			spec = CategorySpecification.equal(firstEqualFilters);
			
			for (int i = 1; i < equalFilters.size(); i++) {
				spec = spec.and(CategorySpecification.equal(equalFilters.get(i)));
			}
		}
		
		var productPage = categoryRepository.findAll(spec, pageable);		
		var pageModel = new PageModel<>(productPage);
		
		return pageModel;
	}

}
