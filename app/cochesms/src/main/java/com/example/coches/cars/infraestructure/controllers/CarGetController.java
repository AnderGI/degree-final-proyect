package com.example.coches.cars.infraestructure.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.coches.cars.application.get_all_cars.AllCarsSearcher;
import com.example.coches.cars.application.get_car.OneCarSearcher;
import com.example.coches.cars.application.search_car_by_criteria.CarCriteriaSearcher;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.convert_car_model_to_json_model.CarToJsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
final public class CarGetController {
	@Autowired
	private CarRepository repo;
	@Autowired
	private ObjectMapper mapper;

	@GetMapping("/cars/criteria")
	public List<ObjectNode> getCarsByCriteria(@RequestParam(required = true) String filters,
			@RequestParam(required = true) String orderBy, @RequestParam(required = true) String orderType) {
		List<ObjectNode> carObjectNodes = new ArrayList<>();
		for (Car car : 
				CarCriteriaSearcher.search_car_by_criteria(repo, filters, orderBy, orderType)
		) {
			carObjectNodes.add(CarToJsonConverter.convert(car, mapper));
		}
		return carObjectNodes;
		
	}

	@GetMapping("/cars")
	public List<ObjectNode> getCars() {
		List<ObjectNode> carObjectNodes = new ArrayList<>();
		for (Car car : AllCarsSearcher.get_all_cars(repo)) {
			carObjectNodes.add(CarToJsonConverter.convert(car, mapper));
		}
		return carObjectNodes;
	}

	@GetMapping("/cars/{id}")
	public ResponseEntity<ObjectNode> getCar(@PathVariable String id) {
		Car requestedCar = OneCarSearcher.get_one_car_by_id(repo, id);
		if (requestedCar == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(CarToJsonConverter.convert(requestedCar, mapper));
	}
	
	@GetMapping("/cars/brands")
	public List<String> getAllCarBrands(){
		return repo.getAllBrands();
	}

}
