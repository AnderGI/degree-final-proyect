package com.example.coches.cars.infraestructure.adapters.cars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
	void it_should_retreive_all_cars_as_a_list(){
	
		System.out.println(repository.getCars());
		assertNotNull(repository.getCars()); 
	}
	/*
		void it_should_save_a_new_car_with_all_values() {
	    Car newCar = new Car(new CarTitle("Coche 2"),
	            new CarDescription("Descripcción del coche 2"),
	            new CarBrand("bmw"), 
	            new CarPrice(0.0),	         
	            new CarUrl(null),
	            new CarUrl(null));
	    
	    Car addedCar = repository.addCar(newCar);
	    System.out.println(addedCar);
	    assertNotNull(addedCar);
	    assertNotNull(addedCar.getIdValue());
	}
	*/
	// Other test Save cars with some null values 
	
	
	void it_should_retrieve_a_car_if_it_exists() {
		Car firstCar = repository.getCars().get(0);
		String firstCarId = firstCar.getIdValue();
		Car retrievedCar = repository.getCar(firstCarId);
		assertNotNull(retrievedCar);
		assertEquals(retrievedCar.getIdValue(), firstCarId);
	}
	
}
