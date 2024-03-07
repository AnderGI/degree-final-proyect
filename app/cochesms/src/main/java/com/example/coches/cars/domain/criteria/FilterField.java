package com.example.coches.cars.domain.criteria;

public class FilterField {
	private String value;
	
	public FilterField(String value) {
		this.value = value;
	}
	
	public String getFieldValue() {
		return this.value;
	}
}
