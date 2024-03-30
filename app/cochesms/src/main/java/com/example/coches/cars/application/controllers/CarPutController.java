package com.example.coches.cars.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.convert_car_model_to_json_model.CarToJsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class CarPutController {
	@Autowired
	private CarRepository repository;
	@Autowired
	private ObjectMapper mapper;
	@PutMapping("/cars/{id}")
	public ResponseEntity<ObjectNode> updateCar(@PathVariable String id,
			@RequestBody Car car){
	     Car updatedCar = repository.updateCar(car, id);
	        if (updatedCar == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(CarToJsonConverter.convert(updatedCar, mapper));
	}
	
}
