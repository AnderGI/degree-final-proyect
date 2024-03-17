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

	// Get cars
	@Test
	void it_should_retreive_all_cars_as_a_list() {
		assertNotNull(repository.getCars());
		assertEquals(repository.getCars().getClass(), ArrayList.class);
	}
		
	// Get car by id
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

	// Get car by non existing id --> null
	@Test
	void it_should_retrieve_null_if_car_id_does_not_exists() {
		Car nullableCar = repository.getCar("aaaaa");
		assertEquals(nullableCar, null);
	}

	// Save car
	@Test
	void it_should_save_a_car_and_check_for_car_equality() {
		// Crear los objetos necesarios para pasar al constructor de Car
		CarTitle title = new CarTitle("Ford Mustang GT");
		CarDescription description = new CarDescription("Potente y deportivo");
		CarBrand brand = new CarBrand("Ford");
		CarPrice price = new CarPrice(45000.0);
		CarUrl carImageURL = new CarUrl("https://example.com/ford-mustang-gt.jpg");
		CarUrl carAnnouncementUrl = new CarUrl("https://example.com/ford-mustang-gt-listing");

		// Crear el objeto Car usando el constructor
		Car car = new Car(title, description, brand, price, carImageURL, carAnnouncementUrl);
		Car addedCar = repository.addCar(car);
		assertNotNull(addedCar);
		assertNotNull(addedCar.getIdValue());
		// Todos los campos iguales
		assertEquals(addedCar.getIdValue(), car.getIdValue());
		assertEquals(addedCar.getTitleValue(), car.getTitleValue());
		assertEquals(addedCar.getDescriptionValue(), car.getDescriptionValue());
		assertEquals(addedCar.getBrandValue(), car.getBrandValue());
		assertEquals(addedCar.getCarPriceValue(), car.getCarPriceValue());
		assertEquals(addedCar.getCarImageUrlValue(), car.getCarImageUrlValue());
		assertEquals(addedCar.getCarAnnouncmentURLValue(), car.getCarAnnouncmentURLValue());
		
	}
	
	
	@Test
	void it_should_delete_last_car_and_check_for_equality() {
		List<Car> cars = repository.getCars();
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
