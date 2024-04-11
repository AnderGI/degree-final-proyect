package com.example.coches.cars.infraestructure.adapters.get.get_cars_by_criteria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.criteria.Criteria;

@SpringBootTest
@ActiveProfiles("mongodbrepository")
public class GetAllCarsByCriteriaIntegrationTester {
	@Autowired
	private CarRepository repository;
	
	@Test
	void it_should_return_a_list_of_cars_by_an_specific_car_title_criteria() {
		System.out.println("it_should_return_a_list_of_cars_by_an_specific_criteria");
		String BRAND_EQUALS_BMW_FILTER = "%5B%0D%0A%7B%0D%0A++%22field%22%3A%22brand%22%2C%0D%0A++%22operator%22%3A%22%3D%22%2C%0D%0A++%22value%22%3A%22bmw%22%0D%0A%7D%0D%0A%5D";
		
		Criteria criteria = Criteria.fromPrimitives(
				BRAND_EQUALS_BMW_FILTER, 
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
