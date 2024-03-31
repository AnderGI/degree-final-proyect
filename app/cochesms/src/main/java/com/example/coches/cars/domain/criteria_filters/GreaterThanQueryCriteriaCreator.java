package com.example.coches.cars.domain.criteria_filters;

import java.text.NumberFormat;

import org.springframework.data.mongodb.core.query.Criteria;

import com.example.coches.cars.domain.criteria.Filter;

public class GreaterThanQueryCriteriaCreator implements MongoQueryCriteriaCreator{
	// En este caso no seria necesaria mirar si es un campo numerico o no
	@Override
	public Criteria createQuery(Filter filter) {
		String filterField = filter.getField().getFieldValue();
		String filterValue = filter.getValue().getFilterValue();
		// IMPORTANTE AUN ASI VALIDAR BIEN ESTO
		Double toNumberFilterValue = Double.parseDouble(filterValue);
		// TODO Auto-generated method stub
		return Criteria.where(filterField +  ".value")
				.gt(toNumberFilterValue);
	}

}
