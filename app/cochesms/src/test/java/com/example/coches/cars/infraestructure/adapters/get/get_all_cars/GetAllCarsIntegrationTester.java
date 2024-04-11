package com.example.coches.cars.infraestructure.adapters.get.get_all_cars;

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
public class GetAllCarsIntegrationTester {
	@Autowired
	private CarRepository repository;
	
	@Test
	void it_should_retreive_all_cars_as_a_list() {
		System.out.println("it_should_retreive_all_cars_as_a_list");
		List<Car> cars = repository.getCars();
		System.out.println(cars);
		assertNotNull(cars);
		assertEquals(cars.getClass(), ArrayList.class);
	}
}
