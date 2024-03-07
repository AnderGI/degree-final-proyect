package com.example.coches.cars.domain.criteria;

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
