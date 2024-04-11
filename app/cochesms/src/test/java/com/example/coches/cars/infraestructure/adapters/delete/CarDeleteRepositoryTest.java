package com.example.coches.cars.infraestructure.adapters.delete;

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
public class CarDeleteRepositoryTest {
	@Autowired
	private CarRepository repository;
	
	// Delete car
	// @Test
	void it_should_delete_last_car_and_check_for_equality() {
		List<Car> cars = repository.getCars();
		if (!cars.isEmpty()) {
			Car lastCar = cars.get(cars.size() - 1);
			Car deletedCar = repository.deleteCar(lastCar.getIdValue());
			assertNotNull(deletedCar);
			assertNotNull(deletedCar.getIdValue());
			// Todos los campos iguales del coche escogido y del eliminado
			assertEquals(deletedCar.getIdValue(), lastCar.getIdValue());
			assertEquals(deletedCar.getTitleValue(), lastCar.getTitleValue());
			assertEquals(deletedCar.getDescriptionValue(), lastCar.getDescriptionValue());
			assertEquals(deletedCar.getBrandValue(), lastCar.getBrandValue());
			assertEquals(deletedCar.getCarPriceValue(), lastCar.getCarPriceValue());
			assertEquals(deletedCar.getCarImageUrlValue(), lastCar.getCarImageUrlValue());
			assertEquals(deletedCar.getCarAnnouncmentURLValue(), lastCar.getCarAnnouncmentURLValue());
			// Valorar si se ha eliminado
			assertEquals(repository.getCar(deletedCar.getIdValue()), null);
		}
	}

	// Delete car with non existing id -> null
	@Test
	void it_should_return_null_if_non_existing_id_is_used_for_deletion() {
		String nonExistingId = "bbbbbbb";
		Car toDeleteNullCar = repository.getCar(nonExistingId);
		assertEquals(toDeleteNullCar, null);
	}
}
