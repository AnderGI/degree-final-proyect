package com.example.coches.cars.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.coches.cars.application.convertCarToJson.CarToJsonConverter;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class CarDeleteController {
	@Autowired
	private CarRepository repo;
	@Autowired
	private ObjectMapper mapper;
	
	@DeleteMapping("/cars/{id}")
	public ResponseEntity<ObjectNode> deleteCar(@PathVariable String id){
		Car toDeleteCar = repo.getCar(id);
		if(toDeleteCar == null) return ResponseEntity.notFound().build();
		repo.deleteCar(toDeleteCar.getIdValue());
		return ResponseEntity.ok(
				CarToJsonConverter.convert_car_to_json(toDeleteCar, mapper));
	}
}
