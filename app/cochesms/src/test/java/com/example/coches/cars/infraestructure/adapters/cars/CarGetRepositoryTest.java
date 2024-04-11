package com.example.coches.cars.infraestructure.adapters.cars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.criteria.Criteria;
import com.example.coches.cars.domain.criteria.Filter;
import com.example.coches.cars.domain.criteria.FilterField;
import com.example.coches.cars.domain.criteria.FilterOperator;
import com.example.coches.cars.domain.criteria.FilterValue;
import com.example.coches.cars.domain.criteria.Filters;
import com.example.coches.cars.domain.criteria.Order;
import com.example.coches.cars.domain.criteria.OrderBy;
import com.example.coches.cars.domain.criteria.OrderType;

@SpringBootTest
@ActiveProfiles("mongodbrepository")
public class CarGetRepositoryTest {
	@Autowired
	private CarRepository repository;

	// Get cars
	@Test
	void it_should_retreive_all_cars_as_a_list() {
		System.out.println("it_should_retreive_all_cars_as_a_list");
		List<Car> cars = repository.getCars();
		System.out.println(cars);
		assertNotNull(cars);
		assertEquals(cars.getClass(), ArrayList.class);
	}

	// Get car by id
	@Test
	void it_should_retrieve_a_existing_car_by_its_id() {
		System.out.println("it_should_retrieve_a_existing_car_by_its_id");
		List<Car> cars = repository.getCars();
		System.out.println(cars);
		if (!cars.isEmpty()) {
			Car firstCar = cars.get(0);
			System.out.println("firstCar");
			System.out.println(firstCar);
			Car retrievedFirstCar = repository.getCar(firstCar.getIdValue());
			System.out.println("retrievedFirstCar");
			System.out.println(retrievedFirstCar);
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
	}

	// Get car by non existing id --> null
	@Test
	void it_should_retrieve_null_if_car_id_does_not_exists() {
		System.out.println("it_should_retrieve_null_if_car_id_does_not_exists");
		Car nullableCar = repository.getCar("aaaaa");
		System.out.println(nullableCar);
		assertEquals(nullableCar, null);
	}

	@Test
	void it_should_return_a_list_of_cars_by_an_specific_car_title_criteria() {
		System.out.println("it_should_return_a_list_of_cars_by_an_specific_criteria");

		Criteria criteria = Criteria.fromPrimitives(
				// String que representa la codificacionm de un array con
				// {
				//  "field":"brand",
				//  "operator":"=",
				//  "value":"bmw"
				// }
				"%5B%0D%0A%7B%0D%0A++%22field%22%3A%22brand%22%2C%0D%0A++%22operator%22%3A%22%3D%22%2C%0D%0A++%22value%22%3A%22bmw%22%0D%0A%7D%0D%0A%5D", 
				"price", 
				"asc");
		
		List<Car> cars = repository.matching(criteria);
		for (Car toTestCar : cars) {
			assertEquals(toTestCar.getBrandValue(), "bmw");
		}
	}
	
	
	@Test
	void it_should_return_a_list_of_cars_by_descending_ordering_of_price() {
		String PRICE_GREATER_THAN_50000_FILTER = "%5B%0D%0A%7B%0D%0A++%22field%22%3A%22price%22%2C%0D%0A++%22operator%22%3A%22%3E%22%2C%0D%0A++%22value%22%3A%2250000%22%0D%0A%7D%0D%0A%5D";
		Criteria order_by_price_desc = Criteria.fromPrimitives(PRICE_GREATER_THAN_50000_FILTER,
				"price", "desc");
		List<Car> cars = repository.matching(order_by_price_desc);
		
		// Para testear que todos los elementos de manera consecutiva 
		// estén ordenados por precio en descendentes
		// Desde el primero hasta el penúltimo comparamos precio
		// el priemro con el segundo ... hasta el penúltimo con el último
		for(int firstIndex = 0; firstIndex < cars.size() - 2; firstIndex++) {
			int next = firstIndex + 1;
			Car first = cars.get(firstIndex);
			Car second = cars.get(next);
			boolean is_first_price_higher_or_equal_than_second = 
					first.getCarPriceValue() >= second.getCarPriceValue();
			assertEquals(is_first_price_higher_or_equal_than_second, true);
		}
	}
	
	
	@Test
	void it_should_return_a_list_of_cars_by_ascending_ordering_of_price() {
		String PRICE_GREATER_THAN_50000_FILTER = "%5B%0D%0A%7B%0D%0A++%22field%22%3A%22price%22%2C%0D%0A++%22operator%22%3A%22%3E%22%2C%0D%0A++%22value%22%3A%2250000%22%0D%0A%7D%0D%0A%5D";
		Criteria order_by_price_desc = Criteria.fromPrimitives(PRICE_GREATER_THAN_50000_FILTER,
				"price", "asc");
		List<Car> cars = repository.matching(order_by_price_desc);
		
		// Para testear que todos los elementos de manera consecutiva 
		// estén ordenados por precio en descendentes
		// Desde el primero hasta el penúltimo comparamos precio
		// el priemro con el segundo ... hasta el penúltimo con el último
		for(int firstIndex = 0; firstIndex < cars.size() - 2; firstIndex++) {
			int next = firstIndex + 1;
			Car first = cars.get(firstIndex);
			Car second = cars.get(next);
			boolean is_first_price_lower_or_equal_than_second = 
					first.getCarPriceValue() <= second.getCarPriceValue();
			assertEquals(is_first_price_lower_or_equal_than_second, true);
		}
	}
	
	
}
