package com.spring.filter.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.filter.course.domain.Category;
import com.spring.filter.course.service.CategoryService;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> list() {
		var categories = categoryService.list();
		
		return ResponseEntity.ok(categories);
	}

}
