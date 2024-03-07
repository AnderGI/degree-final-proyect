package com.example.coches.cars.domain.criteria;

import java.util.List;

final public class Filters {
	private List<Filter> filters;
	public Filters(List filters) {
		this.filters = filters;
	}
	public void add(Filter filter)
	{
		filters.add(filter);
	}
}
