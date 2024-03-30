package com.example.coches.cars.application.search_car_by_criteria;

import java.util.List;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.criteria.Criteria;

final public class CarCriteriaSearcher {
	
	final public static List<Car> search_car_by_criteria(CarRepository repo, String fields, String orderBy, String orderType){
		Criteria criteria = Criteria.fromPrimitives(fields, orderBy, orderType);
		
		return repo.matching(criteria);
	}
}
