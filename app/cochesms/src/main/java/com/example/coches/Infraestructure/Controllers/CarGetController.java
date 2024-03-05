package com.example.coches.Infraestructure.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coches.Domain.Entities.Car;
import com.example.coches.Domain.Repositories.CarRepository;

@RestController
final public class CarGetController {
	@Autowired
	private CarRepository repo;
	
	@GetMapping("/")
	public List<Car> getCars(){
		return repo.getCars();
	}
}
