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
	
	@Test
	void it_should_save_a_new_car() {
	    Car newCar = new Car(new CarTitle("Coche 1"),
	            new CarDescription("Descripcción del coche 1"),
	            new CarBrand("mercedes"), 
	            new CarPrice("17000.25"), null, null);
	    
	    Car addedCar = repository.addCar(newCar);
	    assertNotNull(addedCar);
	    assertNotNull(addedCar.getIdValue());
	    
	    // Verificar si el automóvil se ha agregado correctamente
	    Car retrievedCar = repository.getCar(addedCar.getIdValue());
	    assertNotNull(retrievedCar);
	    assertEquals(newCar.getTitleValue(), retrievedCar.getTitleValue());
	    // Asegúrate de verificar todos los atributos necesarios
	}
	

	
}
