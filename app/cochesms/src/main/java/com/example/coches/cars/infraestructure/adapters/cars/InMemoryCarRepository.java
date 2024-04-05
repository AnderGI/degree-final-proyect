package com.example.coches.cars.infraestructure.adapters.cars;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.criteria.Criteria;

@Repository
public class InMemoryCarRepository implements CarRepository {
	private List<Car> cars = new ArrayList();

	@Override
	public Car getCar(String id) {
		// TODO Auto-generated method stub
		return cars.stream().filter(c -> c.getIdValue().equalsIgnoreCase(id)).findFirst().orElse(null);
	}

	@Override
	public List<Car> getCars() {
		// TODO Auto-generated method stub
		return this.cars;
	}

	@Override
	public Car updateCar(Car car, String id) {
		// TODO Auto-generated method stub

		Integer indexOfCarToUpdate = getIndexOfCarFromId(id);
		if (indexOfCarToUpdate == null)
			return null;

		Car newCar = cars.set(indexOfCarToUpdate, car);
		return newCar;
	}

	@Override
	public Car deleteCar(String id) {
		// TODO Auto-generated method stub
		Integer indexOfCarToDelete = getIndexOfCarFromId(id);
		if (indexOfCarToDelete == null)
			return null;
		Car toRemove = cars.get(indexOfCarToDelete);
		cars.remove(indexOfCarToDelete);
		return toRemove;
	}

	@Override
	public Car addCar(Car car) {
		// TODO Auto-generated method stub
		cars.add(car);
		return car;
	}

	@Override
	public List<Car> matching(Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getIndexOfCarFromId(String id) {
		Car car = cars.stream().filter(c -> c.getIdValue().equals(id)).findFirst().orElse(null);
		if (car == null)
			return null;
		return cars.indexOf(car);
	}

	@Override
	public List<String> getAllBrands() {
		// TODO Auto-generated method stub
		return cars.stream().map(car -> car.getBrandValue()).toList();
	}

}
