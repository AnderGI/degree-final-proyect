package com.example.coches.cars.domain.criteria;


final public class FilterOperator {
	// CONSTANTES PASARLO A ENUM
	public static final String EQUAL = "=";
	public static final String NOT_EQUAL = "!=";
	public static final String GREATER_THAN = ">";
	public static final String LOWER_THAN = "<";
	public static final String CONTAINS = "LIKE";
	public static final String NOT_CONTAINS = "LIKE";
	public static final String IN = "IN";
	private String value = null;
	
	public FilterOperator(String value) {
		this.value = value;
	}
	
	public String getOperatorValue() {
		return this.value;
	}
}
