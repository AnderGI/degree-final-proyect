package com.example.coches.Domain.Criteria.ValueObjects;

final public class OrderBy {
	private String value;
	
	public OrderBy(String value) {
		this.value = value;
	}
	
	public String getOrderByValue() {
		return this.value;
	}
}
