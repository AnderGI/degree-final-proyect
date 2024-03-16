package com.example.coches.cars.infraestructure.adapters.cars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;

@SpringBootTest
@ActiveProfiles("mongodbrepository")
// testeamos el repositorio de mongodb
// en ubbdd de pruebas -> Test de Integración
public class CarRepositoryTest {

	@Autowired
	private CarRepository repository;

	@Test
	void it_should_retreive_all_cars_as_a_list() {
		assertNotNull(repository.getCars());
		assertEquals(repository.getCars().getClass(), ArrayList.class);
	}

	@Test
	void it_should_retrieve_a_existing_car_by_its_id() {
		Car firstCar = repository.getCars().get(0);
		Car retrievedFirstCar = repository.getCar(firstCar.getIdValue());
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

	@Test
	void it_should_retrieve_null_if_car_id_does_not_exists() {
		Car nullableCar = repository.getCar("aaaaa");
		assertEquals(nullableCar, null);
	}

	/*
	 * void it_should_save_a_new_car_with_all_values() { Car newCar = new Car(new
	 * CarTitle("Coche 2"), new CarDescription("Descripcción del coche 2"), new
	 * CarBrand("bmw"), new CarPrice(0.0), new CarUrl(null), new CarUrl(null));
	 * 
	 * Car addedCar = repository.addCar(newCar); System.out.println(addedCar);
	 * assertNotNull(addedCar); assertNotNull(addedCar.getIdValue()); }
	 */
	// Other test Save cars with some null values

}
