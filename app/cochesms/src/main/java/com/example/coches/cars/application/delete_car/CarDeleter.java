package com.example.coches.cars.application.delete_car;

import com.example.coches.cars.domain.car.CarRepository;

final public class CarDeleter {
	private CarRepository repository;
	public CarDeleter(CarRepository repository) {
		this.repository = repository;
	}
	
	public void deleteCar(String id) {
		repository.deleteCar(id);
	}
}
