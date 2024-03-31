package com.example.coches.cars.domain.criteria_filters;

import org.springframework.data.mongodb.core.query.Criteria;

import com.example.coches.cars.domain.criteria.Filter;

public class LowerThanQueryCriteriaCreator implements MongoQueryCriteriaCreator {

	@Override
	public Criteria createQuery(Filter filter) {
		// TODO Auto-generated method stub
		String filterField = filter.getField().getFieldValue();
		String filterValue = filter.getValue().getFilterValue();
		// IMPORTANTE AUN ASI VALIDAR BIEN ESTO
		Double toNumberFilterValue = Double.parseDouble(filterValue);
		return Criteria.where(filterField + ".value")
				.lt(toNumberFilterValue);
	}

}
