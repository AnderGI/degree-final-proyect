package com.example.coches.cars.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.messagereceiver.MessageReceiver;



@RestController
final public class CarGetController {
	@Autowired
	private CarRepository repo;
	

	
	@GetMapping("/")
	public List<Car> getCars(){
		// return 
		return repo.getCars();
	}
}