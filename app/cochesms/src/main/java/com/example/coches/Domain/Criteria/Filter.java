package com.example.coches.Domain.Criteria;

import com.example.coches.Domain.Criteria.ValueObjects.FilterField;
import com.example.coches.Domain.Criteria.ValueObjects.FilterOperator;
import com.example.coches.Domain.Criteria.ValueObjects.FilterValue;

final public class Filter {
	private FilterField field;
	private FilterOperator operator;
	private FilterValue value;
	
	public Filter(FilterField field, FilterOperator operator, FilterValue value) {
		this.field = field;
		this.operator = operator;
		this.value = value;
	}

}
