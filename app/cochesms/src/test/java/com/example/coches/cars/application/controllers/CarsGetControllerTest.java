package com.example.coches.cars.application.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.coches.cars.application.convertCarToJson.CarToJsonConverter;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarId;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class CarsGetControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CarGetController getController;
	@Autowired
	private ObjectMapper mapper;
	@MockBean
	private CarRepository repository;

	@Test
	void return_empty_array_if_empty_repository() throws Exception {
		// Establecemos una condición de un array vacío
		List<Car> expectedCars = new ArrayList<>();
		
		// Especificamos lo que tiene que hacer cuando se ejecute el metodo
		// mockeamos ese use case
		when(repository.getCars()).thenReturn(expectedCars);
		
		// Realizamos la accion al endpoint especificado
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
		
		// Establecemos una lista de prodcutos que se añadiran al mock del repository
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

		// Le especificamos que cuando llame a / devuelva todas las instancias de ese mock
		when(repository.getCars()).thenReturn(expectedCars);

		//Convertir la lista de coches en una lista de jsosn de cada coche
		
		List<String> cochesJsonString = expectedCars.stream()
				.map((Car car) -> CarToJsonConverter.convert_car_to_json(car, mapper).toString())
				.collect(Collectors.toList());
		
		String cochesArrayToString = cochesJsonString.toString();
		
		// Hacemos la operación de get al endpoint
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

	@Test
	void it_should_return_a_car_when_id_exists_in_request() throws Exception {
		// Especificamos el use case
		Car newCar = new Car(new CarId(UUID.randomUUID().toString()),new CarTitle("Chevrolet Camaro SS"), new CarDescription("Muscle car americano"),
				new CarBrand("Chevrolet"), new CarPrice(55000.0),
				new CarUrl("https://example.com/chevrolet-camaro-ss.jpg"),
				new CarUrl("https://example.com/chevrolet-camaro-ss-listing"));
		repository.addCar(newCar);
		
		// When
		when(repository.getCar(newCar.getIdValue())).thenReturn(newCar);
		
		// Endpoint
		mockMvc.perform(
				get("/cars/{id}", newCar.getIdValue()
				).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpectAll(
						status().isOk(),
						content().contentType(MediaType.APPLICATION_JSON),
						content().json(CarToJsonConverter.convert_car_to_json(newCar, mapper).toString())
				);
	}

	@Test
	void it_should_return_not_found_if_id_does_not_exist() throws Exception{
		// When
		when(repository.getCar("fakeId")).thenReturn(null);
		// Endpoint
		mockMvc.perform(
				get("/cars/{id}", "fakeId")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpectAll(
				status().isNotFound()
				);
	}

}
