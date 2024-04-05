package com.example.coches.cars.domain.car;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.coches.cars.domain.criteria.Criteria;

public interface CarRepository {
	Car getCar(String id);
	List<Car> getCars();
	Car updateCar(Car car, String id);
	Car deleteCar(String id);
	Car addCar(Car car);
	List<Car> matching(Criteria criteria); 
	List<String> getAllBrands();
}
