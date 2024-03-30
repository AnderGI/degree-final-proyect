package com.example.coches.cars.domain.criteria;

import lombok.Data;

@Data
final public class Filter {
	private FilterField field;
	private FilterOperator operator;
	private FilterValue value;
	
	public Filter(FilterField field, FilterOperator operator, FilterValue value) {
		this.field = field;
		this.operator = operator;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Filter [field=" + field.getFieldValue() + ", operator=" 
	+ operator.getOperatorValue() + ", value=" + value.getFilterValue() + "]";
	}

}
