package com.example.coches.Domain.Criteria.ValueObjects;

public class FilterField {
	private String value;
	
	public FilterField(String value) {
		this.value = value;
	}
	
	public String getFieldValue() {
		return this.value;
	}
}
