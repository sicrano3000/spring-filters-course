package com.spring.filter.course.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PageModel<T> implements Serializable {
	
	private static final long serialVersionUID = -3457643455547751958L;
	
	private Long totalElements;
	private Integer currentPage;
	private Integer totalPages;
	private Integer pageSize;
	private List<T> elements;

}
