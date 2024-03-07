package com.example.coches.cars.domain.criteria;

final public class OrderBy {
	private String value;
	
	public OrderBy(String value) {
		this.value = value;
	}
	
	public String getOrderByValue() {
		return this.value;
	}
}
