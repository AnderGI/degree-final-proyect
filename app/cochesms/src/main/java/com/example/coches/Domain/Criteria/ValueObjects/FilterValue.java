package com.example.coches.Domain.Criteria.ValueObjects;

final public class FilterValue {
	private String value;
	
	public FilterValue(String value) {
		this.value = value;
	}
	
	public String getFilterValue() {
		return this.value;
	}
}
