package com.example.coches.cars.domain.criteria;


final public class Criteria {
	private Filters filtros;
	private Order ordenacion;
	
	public Criteria(Filters filtros, Order order) {
		this.filtros = filtros;
		this.ordenacion = order;
	}
	
}
