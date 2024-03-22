package com.example.coches.cars.application.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.coches.cars.application.convertCarToJson.CarToJsonConverter;
import com.example.coches.cars.application.saveCar.CarSaver;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;

import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
final public class CarPostController {
	@Autowired
	private CarRepository repo;
	
	@Autowired
	private ObjectMapper mapper;

	@PostMapping(path = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ObjectNode> addCar(@RequestBody Car car) {
		// Validaciones ¿Value Objects? Deberian de hacerse allí
		Car validCar = CarSaver.validateCar(car);
		if(validCar == null) return ResponseEntity.badRequest().build();
		
		Car addedCar = repo.addCar(car);
		URI uri = null;
		try {
			uri = new URI("/cars/" + car.getIdValue());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.created(uri).body(CarToJsonConverter.convert_car_to_json(addedCar, mapper));
	}
}