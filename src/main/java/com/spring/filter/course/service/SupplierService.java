package com.spring.filter.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.filter.course.domain.Supplier;
import com.spring.filter.course.model.FilterModel;
import com.spring.filter.course.model.PageModel;
import com.spring.filter.course.repository.SupplierRepository;

@Service
public class SupplierService implements IListService<Supplier> {

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Override
	public List<Supplier> list() {
		var suppliers = supplierRepository.findAll(); 
		
		return suppliers;
	}

	@Override
	public PageModel<Supplier> list(FilterModel filter) {
		var supplierPage = supplierRepository.findAll(filter.toSpringPageable());		
		var pageModel = new PageModel<>(supplierPage);
		
		return pageModel;
	}

}
