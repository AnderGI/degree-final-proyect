package com.example.coches.cars.infraestructure.adapters.put;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;


@SpringBootTest
@ActiveProfiles("mongodbrepository")
public class CarPutRepositoryTest {
	@Autowired
	private CarRepository repository;
	// Update existing car
	@Test
	void it_should_update_an_existing_car_field() {
		List<Car> cars = repository.getCars();
		if(!cars.isEmpty()) {
			Car toUpdateCar = cars.get(0);
			toUpdateCar.updateDescription("NEW CAR 1 DESCRIPTION");
			Car updatedCar = repository.updateCar(toUpdateCar, toUpdateCar.getIdValue());
			assertNotNull(updatedCar);
			assertEquals(toUpdateCar.getIdValue(), updatedCar.getIdValue());
			assertEquals(toUpdateCar.getTitleValue(), updatedCar.getTitleValue());
			// UPDATED FIELD
			boolean sameCarDescriptionIgnoreCase = updatedCar.getDescriptionValue().equalsIgnoreCase("NEW CAR 1 DESCRIPTION");
			assertEquals(sameCarDescriptionIgnoreCase, true);
			assertEquals(toUpdateCar.getBrandValue(), updatedCar.getBrandValue());
			assertEquals(toUpdateCar.getCarPriceValue(), updatedCar.getCarPriceValue());
			assertEquals(toUpdateCar.getCarImageUrlValue(), updatedCar.getCarImageUrlValue());
			assertEquals(toUpdateCar.getCarAnnouncmentURLValue(), updatedCar.getCarAnnouncmentURLValue());
		}
	}
}
