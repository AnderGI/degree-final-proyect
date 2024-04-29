package com.example.coches.cars.infraestructure.adapters.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarId;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;

@SpringBootTest
@ActiveProfiles("mongodbrepository")
public class CarPostRepositoryTest {

	@Autowired
	private CarRepository repository;
	
	// Return smae car that has been saved
	@Test
	void it_should_return_the_same_car_it_has_saved() {
		 Car toAddCar =  new Car(
				 new CarId(UUID.randomUUID().toString()),
				 new CarTitle("BMW M3"), 
		            new CarDescription("Sedán deportivo de lujo"), 
		            new CarBrand("BMW"), new CarPrice(70000.0), 
		            new CarUrl("https://example.com/bmw-m3.jpg"), 
		            new CarUrl("https://example.com/bmw-m3-listing"));	
		Car addedCar = repository.addCar(toAddCar);
		// completar test
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
		Car car = new Car(new CarId(UUID.randomUUID().toString()),title, description, brand, price, carImageURL, carAnnouncementUrl);
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

}
