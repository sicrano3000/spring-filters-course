package com.spring.filter.course.model;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.spring.filter.course.constant.ApiConstante.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterModel {
	
	private Integer limit;
	private Integer page;
	
	public FilterModel(Map<String, String> params) {
	
		this.limit = params.containsKey(LIMIT_KEY) ? Integer.valueOf(params.get(LIMIT_KEY)) : DEFAULT_LIMIT;
		this.page = params.containsKey(PAGE_KEY) ? Integer.valueOf(params.get(PAGE_KEY)) : DEFAULT_PAGE;
		
	}
	
	public Pageable toSpringPageable() {
		return PageRequest.of(page, limit);
	}
	
}
