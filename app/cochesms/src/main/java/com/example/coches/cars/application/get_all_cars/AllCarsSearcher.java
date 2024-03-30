package com.example.coches.cars.application.get_all_cars;

import java.util.List;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;

final public class AllCarsSearcher {
	
	final public static List<Car> get_all_cars(CarRepository repository){
		return repository.getCars();
	}
}
