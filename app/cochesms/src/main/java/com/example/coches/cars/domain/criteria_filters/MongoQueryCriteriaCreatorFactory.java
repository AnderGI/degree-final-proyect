package com.example.coches.cars.domain.criteria_filters;

import java.util.Map;
import java.util.TreeMap;

import com.example.coches.cars.domain.criteria.Filter;

final public class MongoQueryCriteriaCreatorFactory {
	private Map<String, MongoQueryCriteriaCreator> operatorToCriteriaCreator = new TreeMap<>();
	{
		operatorToCriteriaCreator.put("=", new EqualQueryCriteriaByCreator()); 
		operatorToCriteriaCreator.put(">", new GreaterThanQueryCriteriaCreator()); 
		operatorToCriteriaCreator.put("<", new LowerThanQueryCriteriaCreator()); 
	}
	
	public MongoQueryCriteriaCreator getMongoCriteriaCreator(Filter filter) {
			return operatorToCriteriaCreator.get(
					filter.getOperator().getOperatorValue()
			);
	}
	
}
