package com.spring.filter.course.model;

import static com.spring.filter.course.constant.ApiConstante.DEFAULT_LIMIT;
import static com.spring.filter.course.constant.ApiConstante.DEFAULT_PAGE;
import static com.spring.filter.course.constant.ApiConstante.DEFAULT_SORT;
import static com.spring.filter.course.constant.ApiConstante.LIMIT_KEY;
import static com.spring.filter.course.constant.ApiConstante.PAGE_KEY;
import static com.spring.filter.course.constant.ApiConstante.SORT_KEY;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterModel {
	
	private Integer limit;
	private Integer page;
	private String sort;
	
	public FilterModel(Map<String, String> params) {
	
		this.limit = params.containsKey(LIMIT_KEY) ? Integer.valueOf(params.get(LIMIT_KEY)) : DEFAULT_LIMIT;
		this.page = params.containsKey(PAGE_KEY) ? Integer.valueOf(params.get(PAGE_KEY)) : DEFAULT_PAGE;
		this.sort = params.containsKey(SORT_KEY) ? params.get(SORT_KEY) : DEFAULT_SORT;
		
	}
	
	public Pageable toSpringPageable() {
		var orders = getOrders();
		
		return PageRequest.of(page, limit, Sort.by(orders));
	}
	
	private List<Order> getOrders() {
		List<Order> orders = new ArrayList<>();
		
		var properties = sort.split(",");
		
		for(String property: properties) {
			if (!property.trim().isEmpty()) {
				String column = "";
				
				if (property.startsWith("-")) {
					column = property.replace("-", "").trim();
					orders.add(Order.desc(column));
				} else {
					column = property.trim();
					orders.add(Order.asc(column));
				}
			}
		}
		
		return orders;
	}
	
}
