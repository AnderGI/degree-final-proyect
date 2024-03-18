package com.example.coches.cars.application.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.coches.cars.application.convertCarToJson.CarToJsonConverter;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.messagereceiver.MessageReceiver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;



@RestController
final public class CarGetController {
	@Autowired
	private CarRepository repo;
	@Autowired
	private ObjectMapper mapper;
	@GetMapping("/cars")
	public List<ObjectNode> getCars(){
		List<ObjectNode> nodes = new ArrayList<>();
		for(Car car: repo.getCars()) {	
			nodes.add(CarToJsonConverter.convert_car_to_json(car, mapper));
		}
		// return 
		return nodes;
	}
	@GetMapping("/cars/{id}")
	public ResponseEntity<ObjectNode> getCar(@PathVariable String id) {
	    Car requestedCar = repo.getCar(id);
	    
	    if (requestedCar == null) {
	        return ResponseEntity.notFound().build();
	    }	    
	    return ResponseEntity.ok(CarToJsonConverter.convert_car_to_json(requestedCar, mapper));
	}

}
