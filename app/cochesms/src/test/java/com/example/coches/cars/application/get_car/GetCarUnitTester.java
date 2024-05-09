package com.example.coches.cars.application.get_car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.coches.cars.application.add_car.CarSaver;
import com.example.coches.cars.domain.Mother.CarBrandMother;
import com.example.coches.cars.domain.Mother.CarDescriptionMother;
import com.example.coches.cars.domain.Mother.CarIdMother;
import com.example.coches.cars.domain.Mother.CarImageUrlMother;
import com.example.coches.cars.domain.Mother.CarMother;
import com.example.coches.cars.domain.Mother.CarPriceMother;
import com.example.coches.cars.domain.Mother.CarTitleMother;
import com.example.coches.cars.domain.Mother.CarUrlMother;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarId;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.example.coches.cars.infraestructure.adapters.cars.MongoDBCarRepository;
import com.github.javafaker.Faker;

// Cada test va a ser atómico por lo tanto hay que ejecutar todos los pasos
// necesario que contemplen ese test
// En el caso de realizar un GET a un coche especifico es necesario primero añadirlo
public class GetCarUnitTester {
	@Test
	void it_should_get_an_existing_car() throws Exception {
		// Mockeamos repositorio
		CarRepository repo = Mockito.mock(MongoDBCarRepository.class);
		Car car = CarMother.create();
		
		// Instanciamos el caso de uso de guardar
		CarSaver saver = new CarSaver(repo);
		
		// Lo guardamos y de paso testeamos que no peta añadiendo el trhows
		saver.save_car(car);
		
		// Caso de uso de buscar coche por id
		// INCONSISTENCIAS CON LA INSTANCIACIÓN, NECESARIO REFACTOR
		OneCarSearcher searcher = new OneCarSearcher();
		
		when(searcher.get_one_car_by_id(repo, car.getIdValue())).thenReturn(car);
		
		Car retrievedCar = searcher.get_one_car_by_id(repo, car.getIdValue());
		
		assertEquals(car, retrievedCar);

	}

	@Test
	void it_should_not_get_an_inexistent_car() {
		// Mockeamos repositorio
		CarRepository repo = Mockito.mock(MongoDBCarRepository.class);
		// Caso de uso de buscar coche por id
		// INCONSISTENCIAS CON LA INSTANCIACIÓN, NECESARIO REFACTOR
		OneCarSearcher searcher = new OneCarSearcher();
		when(searcher.get_one_car_by_id(repo, "fakeId")).thenReturn(null);
		Car retrievedCar = searcher.get_one_car_by_id(repo, "fakeId");
		assertNull(retrievedCar);

	}

}
