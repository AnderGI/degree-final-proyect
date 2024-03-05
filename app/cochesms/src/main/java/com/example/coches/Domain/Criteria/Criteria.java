package com.example.coches.Domain.Criteria;

import com.example.coches.Domain.ValueObjects.CarBrand;
import com.example.coches.Domain.ValueObjects.CarDescription;
import com.example.coches.Domain.ValueObjects.CarPrice;
import com.example.coches.Domain.ValueObjects.CarTitle;
import com.example.coches.Domain.ValueObjects.CarUrl;

final public class Criteria {
	private Filters filtros;
	private Order ordenacion;
	
	public Criteria(Filters filtros, Order order) {
		this.filtros = filtros;
		this.ordenacion = order;
	}
	
}
