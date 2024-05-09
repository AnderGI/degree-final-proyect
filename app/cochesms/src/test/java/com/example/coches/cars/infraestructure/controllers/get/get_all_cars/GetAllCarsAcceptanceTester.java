package com.example.coches.cars.infraestructure.controllers.get.get_all_cars;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.example.coches.cars.infraestructure.controllers.CarGetController;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class GetAllCarsAcceptanceTester {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CarGetController getController;
	@Autowired
	private ObjectMapper mapper;
	@MockBean
	private CarRepository repository;
	
	// Casos : Que el array este vacio o que no lo este
	
	@Test
	void return_empty_array_if_empty_repository() throws Exception {
		List<Car> expectedCars = new ArrayList<>();
		
		when(repository.getCars()).thenReturn(expectedCars);
		
		mockMvc.perform(get("/cars").accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpectAll(
				status().isOk(),
				content().contentType(MediaType.APPLICATION_JSON),
				content().json(mapper.writeValueAsString(expectedCars))
		);
	}

	@Test
	void return_a_list_of_products_if_repository_is_not_empty() throws Exception {
		List<Car> expectedCars = Arrays.asList(
				new Car(new CarId(UUID.randomUUID().toString()),new CarTitle("Chevrolet Camaro SS"), new CarDescription("Muscle car americano"),
						new CarBrand("Chevrolet"), new CarPrice(55000.0),
						new CarUrl("https://example.com/chevrolet-camaro-ss.jpg"),
						new CarUrl("https://example.com/chevrolet-camaro-ss-listing")),
				new Car(new CarId(UUID.randomUUID().toString()),new CarTitle("BMW M3"), new CarDescription("Sedán deportivo de lujo"), new CarBrand("BMW"),
						new CarPrice(70000.0), new CarUrl("https://example.com/bmw-m3.jpg"),
						new CarUrl("https://example.com/bmw-m3-listing")),
				new Car(new CarId(UUID.randomUUID().toString()),new CarTitle("Audi R8"), new CarDescription("Supercar de alta gama"), new CarBrand("Audi"),
						new CarPrice(150000.0), new CarUrl("https://example.com/audi-r8.jpg"),
						new CarUrl("https://example.com/audi-r8-listing")));
		repository.addCar(expectedCars.get(0));
		repository.addCar(expectedCars.get(1));
		repository.addCar(expectedCars.get(2));

	
		// Del array de expectedCars lo pasamos a una mapeador
		// Convierte a ObjectNode y luego al string 
		// creamos una lista y luego su representacion como string
		String cochesArrayToString = expectedCars.stream()
				.map((Car car) -> CarToJsonConverter.convert(car, mapper).toString())
				.collect(Collectors.toList())
				.toString();
		
		when(repository.getCars()).thenReturn(expectedCars);

	
		mockMvc.perform(
					get("/cars")
					.accept(MediaType.APPLICATION_JSON)
				)
				.andDo(print())
				.andExpectAll(
						status().isOk(),
						content().contentType(MediaType.APPLICATION_JSON),
						content().json(cochesArrayToString)
						);
	}
}
