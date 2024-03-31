package com.example.coches.cars.domain.criteria_filters;



import org.springframework.data.mongodb.core.query.Criteria;

import com.example.coches.cars.domain.criteria.Filter;

public interface MongoQueryCriteriaCreator {
	Criteria createQuery(Filter filter);
}
