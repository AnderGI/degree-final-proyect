package com.example.coches.cars.infraestructure.controllers.get.get_cars_by_criteria;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarId;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.example.coches.cars.domain.convert_car_model_to_json_model.CarToJsonConverter;
import com.example.coches.cars.domain.criteria.Criteria;
import com.example.coches.cars.domain.criteria.Filter;
import com.example.coches.cars.domain.criteria.FilterField;
import com.example.coches.cars.domain.criteria.FilterOperator;
import com.example.coches.cars.domain.criteria.FilterValue;
import com.example.coches.cars.domain.criteria.Filters;
import com.example.coches.cars.infraestructure.controllers.CarGetController;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class GetCarsByCriteriaAcceptanceTester {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CarGetController getController;
	@Autowired
	private ObjectMapper mapper;
	@MockBean
	private CarRepository repository;
	// Este test falla pero el resultado en produccion es el esperado
	@Test
	void it_should_return_only_cars_that_meet_brand_criteria() throws Exception{
		List<Car> cars = Arrays.asList(
				new Car(new CarId(UUID.randomUUID().toString()),new CarTitle("Chevrolet Camaro SS"), new CarDescription("Muscle car americano"),
						new CarBrand("Chevrolet"), new CarPrice(55000.0),
						new CarUrl("https://example.com/chevrolet-camaro-ss.jpg"),
						new CarUrl("https://example.com/chevrolet-camaro-ss-listing")),
				new Car(new CarId(UUID.randomUUID().toString()),new CarTitle("BMW M3"), new CarDescription("Sedán deportivo de lujo"), new CarBrand("BMW"),
						new CarPrice(60000.0), new CarUrl("https://example.com/bmw-m3.jpg"),
						new CarUrl("https://example.com/bmw-m3-listing")),
				new Car(new CarId(UUID.randomUUID().toString()),new CarTitle("Audi R8"), new CarDescription("Supercar de alta gama"), new CarBrand("Audi"),
						new CarPrice(150000.0), new CarUrl("https://example.com/audi-r8.jpg"),
						new CarUrl("https://example.com/audi-r8-listing")),
				new Car(new CarId(UUID.randomUUID().toString()),new CarTitle("BMW E36"), new CarDescription("V8 clásico"), new CarBrand("BMW"),
						new CarPrice(70000.0), new CarUrl("https://example.com/bmw-m3.jpg"),
						new CarUrl("https://example.com/bmw-m3-listing")));
		repository.addCar(cars.get(0));
		repository.addCar(cars.get(1));
		repository.addCar(cars.get(2));
		repository.addCar(cars.get(3));
		System.out.println(repository.getCars());
		String encodedFilterValue = "%5B%7B%22field%22%3A%22price%22%2C%22operator%22%3A%22%3E%22%2C%22value%22%3A%2250000%22%7D%2C%7B%22field%22%3A%22brand%22%2C%22operator%22%3A%22%3D%22%2C%22value%22%3A%22bmw%22%7D%5D";
		String orderByValue = "price";
		String orderTypeValue = "asc";

		Criteria criteria = Criteria.fromPrimitives(encodedFilterValue, orderByValue, orderTypeValue);

		List<Car> expectedCars = Arrays.asList(cars.get(1), cars.get(3));
		String cochesArrayToString = 
				expectedCars.stream()
				.map((Car car) -> CarToJsonConverter.convert(car, mapper).toString())
				.collect(Collectors.toList())
				.toString();
		
		
		when(repository.matching(criteria))
			.thenReturn(Arrays.asList(cars.get(1), cars.get(3)));

		
		mockMvc.perform(
				get("/cars/criteria")
				.param("filters", "[{\"field\":\"price\",\"operator\":\">\",\"value\":\"50000\"},{\"field\":\"brand\",\"operator\":\"=\",\"value\":\"bmw\"}]")
				.param("orderBy", orderByValue)
				.param("orderType", orderTypeValue)
		)
		.andDo(print())
		.andExpectAll(
				status().isOk(),
				content().contentType(MediaType.APPLICATION_JSON),
				content().json(cochesArrayToString)
		);
		
		
	}
	

}
