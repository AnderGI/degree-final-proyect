package com.example.coches.cars.application.add_car;

import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
	void it_should_save_a_correct_car() {
	    Car car =  new Car(new CarId(UUID.randomUUID().toString()), 
	    		new CarTitle("ACTUALIZADO"), 
	            new CarDescription("ACTUALIZADO"), 
	            new CarBrand("BMW"), new CarPrice(70000.0), 
	            new CarUrl("https://example.com/bmw-m3.jpg"), 
	            new CarUrl("https://example.com/bmw-m3-listing"));
		// Mock mongodbrepository
		CarRepository mockMongoDbRepo = Mockito.mock(MongoDBCarRepository.class);
		// Instanciar el caso de uso con el mock del repositorio
		CarSaver saverUseCase = new CarSaver(mockMongoDbRepo);
		// En este caso como es un metodo que devuelve void lo unico que 
		// se testea es que no "pete"
		saverUseCase.save_car(car);
	}
}
