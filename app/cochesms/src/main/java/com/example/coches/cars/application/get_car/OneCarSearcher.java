package com.example.coches.cars.application.get_car;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;

final public class OneCarSearcher {
	final public static Car get_one_car_by_id(CarRepository repo, String id) {
		return repo.getCar(id);
	}
}
