package com.example.coches.cars.application.add_car;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.validate_car.CarValidator;

final public class CarSaver {
	private CarRepository repo;
	
	public CarSaver(CarRepository repo) {
		this.repo = repo;
	}
	
	// Estas excepcciones son genéricas y habría que crear excepciones propias de dominio
	public void save_car(Car car) throws Exception{
			CarValidator.validateCar(car);
			repo.addCar(car);
	}
}
