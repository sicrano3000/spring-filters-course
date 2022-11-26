package com.spring.filter.course.service;

import java.util.List;

import com.spring.filter.course.model.FilterModel;
import com.spring.filter.course.model.PageModel;

public interface IListService<T> {

	public List<T> list();
	
	public PageModel<T> list(FilterModel filter);
	
}
