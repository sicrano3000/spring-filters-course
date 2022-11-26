package com.spring.filter.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.filter.course.domain.Product;
import com.spring.filter.course.model.FilterModel;
import com.spring.filter.course.model.PageModel;
import com.spring.filter.course.repository.ProductRepository;

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
		var productPage = productRepository.findAll(filter.toSpringPageable());		
		var pageModel = new PageModel<>(productPage);
		
		return pageModel;
	}

}
