package com.spring.filter.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.spring.filter.course.domain.Product;
import com.spring.filter.course.model.FilterModel;
import com.spring.filter.course.model.PageModel;
import com.spring.filter.course.repository.ProductRepository;
import com.spring.filter.course.specification.ProductSpecification;

@Service
public class ProductService implements IListService<Product> {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> list() {
		var products = productRepository.findAll();
		
		return products;
	}

	@Override
	public PageModel<Product> list(FilterModel filter) {
		Specification<Product> spec = null;
		var pageable = filter.toSpringPageable();	
		var equalFilters = filter.getEqualFilters(); 
		
		if (!equalFilters.isEmpty()) {
			var firstEqualFilters = equalFilters.get(0);
			spec = ProductSpecification.equal(firstEqualFilters);
			
			for (int i = 1; i < equalFilters.size(); i++) {
				spec = spec.and(ProductSpecification.equal(equalFilters.get(i)));
			}
		}
		
		var productPage = productRepository.findAll(spec, pageable);		
		var pageModel = new PageModel<>(productPage);
		
		return pageModel;
	}

}
