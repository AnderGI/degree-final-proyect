package com.example.coches.Domain.Repositories;

import java.util.List;

import com.example.coches.Domain.Entities.Car;

public interface CarRepository {
	Car getCar(String id);
	List<Car> getCars();
	Car updateCar(Car car, String id);
	Car deleteCar(String id);
	Car addCar(Car car);
	List<Car> findBy(); //Specification / Criteria Pattern 
}
