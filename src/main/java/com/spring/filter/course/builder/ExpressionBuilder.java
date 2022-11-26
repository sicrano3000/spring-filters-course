package com.spring.filter.course.builder;

import java.util.Arrays;
import java.util.regex.Pattern;

import com.spring.filter.course.constant.ApiConstante;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;

public class ExpressionBuilder<T> {
	
	Class<T> superClass;
	
	public ExpressionBuilder(Class<T> superClass) {
		this.superClass = superClass;
	}
	
	public Expression<T> get(Root<T> root, String field) {
		try {
			if (!field.contains(ApiConstante.DOT) ) {
				if (containFiled(superClass, field)) {
					return root.get(field);
				}
			} else {
				var fields = field.split(Pattern.quote(ApiConstante.DOT));
				
				if (fields.length == 2) {
					var field1 = fields[0];
					var field2 = fields[1];
					
					if (containFiled(superClass, field1)) {
						var classField = superClass.getDeclaredField(field1);
						classField.setAccessible(true);
						
						var subClass = classField.getType();
						
						if (containFiled(subClass, field2)) {
							return root.get(field1).get(field2);
						}
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		
		return null;
	}
	
	private Boolean containFiled(Class<?> anyClass, String filed) {
		var contain = false;
		
		contain = Arrays.asList(anyClass.getDeclaredFields())
			  .stream()
			  .anyMatch(df -> df.getName().equals(filed));
		
		return contain;
	}

}
