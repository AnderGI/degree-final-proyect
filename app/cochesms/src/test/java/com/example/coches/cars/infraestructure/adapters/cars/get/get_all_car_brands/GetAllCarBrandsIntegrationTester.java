package com.example.coches.cars.infraestructure.adapters.cars.get.get_all_car_brands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.coches.cars.domain.car.CarRepository;

@SpringBootTest
@ActiveProfiles("mongodbrepository")
public class GetAllCarBrandsIntegrationTester {
	@Autowired
	private CarRepository repository;
	@Test
	void it_should_retreive_all_car_brands_as_string_array() {
		List<String> brands = repository.getAllBrands();
		System.out.println(brands);
		assertNotNull(brands);
	}
}
