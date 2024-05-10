package com.example.coches.cars.application.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.coches.cars.application.add_car.CarSaver;
import com.example.coches.cars.application.delete_car.CarDeleter;
import com.example.coches.cars.domain.Mother.CarMother;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.infraestructure.adapters.cars.MongoDBCarRepository;

public class DeleteCarUnitTester {
	
	// Delete car
	@Test
	void it_should_delete_existing_car() throws Exception{
		CarRepository repository = Mockito.mock(MongoDBCarRepository.class);
		// caso de uso de eliminar
		
		Car toAddCar = CarMother.create();
		
		CarSaver saver = new CarSaver(repository);
		
		saver.save_car(toAddCar); // no lanza ninguna excepcción
		
		CarDeleter deleter = new CarDeleter(repository);
		
		deleter.deleteCar(toAddCar.getIdValue());
		
	}

}
