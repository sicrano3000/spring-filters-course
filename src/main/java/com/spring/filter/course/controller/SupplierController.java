package com.spring.filter.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.filter.course.domain.Supplier;
import com.spring.filter.course.service.SupplierService;

@RestController
@RequestMapping(value = "suppliers")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping
	public ResponseEntity<List<Supplier>> list() {
		var suppliers = supplierService.list();
		
		return ResponseEntity.ok(suppliers);
	}

}
