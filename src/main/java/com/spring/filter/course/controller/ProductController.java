package com.spring.filter.course.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.filter.course.domain.Product;
import com.spring.filter.course.model.FilterModel;
import com.spring.filter.course.model.PageModel;
import com.spring.filter.course.service.ProductService;

@RestController
@RequestMapping(value = "products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<PageModel<Product>> list(@RequestParam Map<String, String> params) {
		var filter = new FilterModel(params);
		var products = productService.list(filter);
		
		return ResponseEntity.ok(products);
	}

}
