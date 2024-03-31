package com.example.coches.cars.domain.criteria_filters;

import org.springframework.data.mongodb.core.query.Criteria;

import com.example.coches.cars.domain.criteria.Filter;

public class EqualQueryCriteriaByCreator implements MongoQueryCriteriaCreator {

	@Override
	public Criteria createQuery(Filter filter) {
		
		// Tendria que mirar si el campo se puede parsear a un numero o no
		// para determinar si el campo es numerico
		// TODO Auto-generated method stub
		String filterField = filter.getField().getFieldValue();
		String filterValue = filter.getValue().getFilterValue();
		return Criteria.where(filterField + ".value")
				.is(filterValue);
	}

}
