package com.example.coches.cars.infraestructure.adapters.cars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;

@SpringBootTest
@ActiveProfiles("mongodbrepository")
public class CarGetRepositoryTest {
	@Autowired
	private CarRepository repository;

	// Get cars
	@Test
	void it_should_retreive_all_cars_as_a_list() {
		System.out.println("it_should_retreive_all_cars_as_a_list");
		List<Car> cars = repository.getCars();
		System.out.println(cars);
		assertNotNull(cars);
		assertEquals(cars.getClass(), ArrayList.class);
	}

	// Get car by id
	@Test
	void it_should_retrieve_a_existing_car_by_its_id() {
		System.out.println("it_should_retrieve_a_existing_car_by_its_id");
		List<Car> cars = repository.getCars();
		System.out.println(cars);
		if (!cars.isEmpty()) {
			Car firstCar = cars.get(0);
			System.out.println("firstCar");
			System.out.println(firstCar);
			Car retrievedFirstCar = repository.getCar(firstCar.getIdValue());
			System.out.println("retrievedFirstCar");
			System.out.println(retrievedFirstCar);
			assertNotNull(retrievedFirstCar);
			assertNotNull(retrievedFirstCar.getIdValue());
			// Todos los campos iguales
			assertEquals(firstCar.getIdValue(), retrievedFirstCar.getIdValue());
			assertEquals(firstCar.getTitleValue(), retrievedFirstCar.getTitleValue());
			assertEquals(firstCar.getDescriptionValue(), retrievedFirstCar.getDescriptionValue());
			assertEquals(firstCar.getBrandValue(), retrievedFirstCar.getBrandValue());
			assertEquals(firstCar.getCarPriceValue(), retrievedFirstCar.getCarPriceValue());
			assertEquals(firstCar.getCarImageUrlValue(), retrievedFirstCar.getCarImageUrlValue());
			assertEquals(firstCar.getCarAnnouncmentURLValue(), retrievedFirstCar.getCarAnnouncmentURLValue());
		}
	}

	// Get car by non existing id --> null
	@Test
	void it_should_retrieve_null_if_car_id_does_not_exists() {
		System.out.println("it_should_retrieve_null_if_car_id_does_not_exists");
		Car nullableCar = repository.getCar("aaaaa");
		System.out.println(nullableCar);
		assertEquals(nullableCar, null);
	}
}
