package com.example.coches.cars.application.add_car;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.coches.cars.domain.Mother.CarMother;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarId;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.example.coches.cars.infraestructure.adapters.cars.MongoDBCarRepository;

// Al ser tests unitarios (partimos desde la capa de aplicacion, subject under test)
// hay que falsear la implementacion del repositorio
// en este caso mediante mocks
public class AddCarUnitTester {
	@Test
	void it_should_save_a_correct_car() throws Exception {
		Car car = CarMother.create();
		// Mock mongodbrepository
		CarRepository mockMongoDbRepo = Mockito.mock(MongoDBCarRepository.class);
		// Instanciar el caso de uso con el mock del repositorio
		CarSaver saverUseCase = new CarSaver(mockMongoDbRepo);
		// En este caso como es un metodo que devuelve void lo unico que
		// se testea es que no "pete" lanzando ninguna excepcción
		saverUseCase.save_car(car);
	}
	
	@Test
	void it_should_not_save_an_incorrect_car() {
		Car invalidCar = null;
		// Mock mongodbrepository
		CarRepository mockMongoDbRepo = Mockito.mock(MongoDBCarRepository.class);
		// Instanciar el caso de uso con el mock del repositorio
		CarSaver saverUseCase = new CarSaver(mockMongoDbRepo);
		// testeare que la excepcion no es null porque va a lanzar una
		try {
			saverUseCase.save_car(invalidCar);
		}catch (Exception e) {
			assertNotNull(e);
		}
	}
}
