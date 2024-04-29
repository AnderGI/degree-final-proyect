package com.example.coches.cars.domain.criteria;

final public class OrderType {
	// CONSTANTES HABRIUA QUE PASARLAS A ENUM
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	public static final String NONE = "none";
	private String value = null;
	
	public OrderType(String value) {
		this.value = value;
	}
	
	public String getOrderTypeValue() {
		return this.value;
	}
}
