package com.example.coches.cars.infraestructure.adapters.cars;

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
		System.out.println("it_should_delete_last_car_and_check_for_equality");
		List<Car> cars = repository.getCars();
		if (!cars.isEmpty()) {
			Car lastCar = cars.get(cars.size() - 1);
			System.out.println("lastCar");
			System.out.println(lastCar);
			Car deletedCar = repository.deleteCar(lastCar.getIdValue());
			System.out.println("deletedCar");
			System.out.println(deletedCar);
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
		System.out.println("it_should_return_null_if_non_existing_id_is_used_for_deletion");
		String nonExistingId = "bbbbbbb";
		Car toDeleteNullCar = repository.getCar(nonExistingId);
		System.out.println("toDeleteNullCar");
		System.out.println(toDeleteNullCar);
		assertEquals(toDeleteNullCar, null);
	}
}
