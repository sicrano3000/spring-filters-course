package com.spring.filter.course.specification;

import org.springframework.data.jpa.domain.Specification;

import com.spring.filter.course.builder.ExpressionBuilder;
import com.spring.filter.course.domain.Supplier;
import com.spring.filter.course.model.EqualFilterModel;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class SupplierSpecification {
	
	public static Specification<Supplier> equal(EqualFilterModel eq) {
		return new Specification<Supplier>() {

			private static final long serialVersionUID = -3787872116359637091L;

			@Override
			public Predicate toPredicate(Root<Supplier> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = null;
				
				var expressionBuilder = new ExpressionBuilder<>(Supplier.class);
				var expression = expressionBuilder.get(root, eq.getColumn());
				
				if (expression != null) {
					predicate = (eq.getIsEqual() ? criteriaBuilder.equal(expression, eq.getValue()) : criteriaBuilder.notEqual(expression, eq.getValue()));
				}
				
				return predicate;
			}
		};
		
	}

}
