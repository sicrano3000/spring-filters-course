package com.spring.filter.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.spring.filter.course.domain.Supplier;
import com.spring.filter.course.model.FilterModel;
import com.spring.filter.course.model.PageModel;
import com.spring.filter.course.repository.SupplierRepository;
import com.spring.filter.course.specification.SupplierSpecification;

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
		Specification<Supplier> spec = null;
		var pageable = filter.toSpringPageable();	
		var equalFilters = filter.getEqualFilters(); 
		
		if (!equalFilters.isEmpty()) {
			var firstEqualFilters = equalFilters.get(0);
			spec = SupplierSpecification.equal(firstEqualFilters);
			
			for (int i = 1; i < equalFilters.size(); i++) {
				spec = spec.and(SupplierSpecification.equal(equalFilters.get(i)));
			}
		}
		
		var productPage = supplierRepository.findAll(spec, pageable);		
		var pageModel = new PageModel<>(productPage);
		
		return pageModel;
	}

}
