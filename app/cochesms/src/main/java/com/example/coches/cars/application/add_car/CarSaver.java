package com.example.coches.cars.application.add_car;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;

final public class CarSaver {
	private CarRepository repo;
	
	public CarSaver(CarRepository repo) {
		this.repo = repo;
	}
	
	public void save_car(Car car) {
		repo.addCar(car);
	}
}
