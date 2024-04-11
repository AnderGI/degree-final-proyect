package com.example.coches.cars.domain.criteria;

import java.util.List;

final public class Criteria {
	private Filters filters;
	private Order order;
	
	public Criteria(Filters filtros, Order order) {
		this.filters = filtros;
		this.order = order;
	}
	
	
	
	public List<Filter> getFilters() {
		return this.filters.getFiltersList();
	}

	public static Criteria fromPrimitives(String filters, String orderBy, String orderType) {
		return new Criteria(
			Filters.fromPrimitives(filters), // esto se pasa como url encoded
			Order.fromPrimitives(orderBy, orderType) // estos dos como strings normales
		);
	}
	
	public String getOrderByValue() {
		return this.order.getOrderBy().getOrderByValue();
	}

	public String getOrderTypeValue() {
		return this.order.getOrderType().getOrderTyppeValue();
	}


	@Override
	public String toString() {
		return "Criteria [filters=" + filters + ", order=" + order + "]";
	}
	
	
	
}
