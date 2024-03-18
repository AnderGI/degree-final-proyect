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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarId;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.fasterxml.jackson.databind.ObjectMapper;

// MOCKING -> MOCKITO
@WebMvcTest
public class CarsGetControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CarGetController getController;
	@Autowired
	private ObjectMapper mapper;
	// Mockeamos una instancia del bean CarRepository para no utilizar el mismo
	@MockBean
	private CarRepository repository;

	@Test
	void it_should_return_empty_array_if_empty_repository() throws Exception {
		List<Car> expectedCars = new ArrayList<>();
		// No añadimos nada
		// Especificamos lo que tiene que hacer cuando se ejecute el metodo
		when(repository.getCars()).thenReturn(expectedCars);
		// Realizamos la accion al endpoint especificado
		mockMvc.perform(get("/cars").accept(MediaType.APPLICATION_JSON)).andDo(print()) // Mostrar por consola
				.andExpectAll(
						// 200 OK
						status().isOk(),
						// Devuelve el contenido como un json
						content().contentType(MediaType.APPLICATION_JSON)
						// Devuelve una lista vacia
						//content().json(mapper.writeValueAsString(expectedCars))
						);
	}

	@Test
	void it_should_return_a_list_of_products_when_get() throws Exception {
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

		// Le especificamos que cuando llame a / devuelva todas las instancias
		when(repository.getCars()).thenReturn(expectedCars);

		// Hacemos la operación de get al endpoint
		mockMvc.perform(get("/cars").accept(MediaType.APPLICATION_JSON)).andDo(print()) // mostrar por consola
				.andExpectAll(
						// Estado correcto 200 OK
						status().isOk(),
						// El contenido se envia como json
						content().contentType(MediaType.APPLICATION_JSON)
						// El contenido es el mismo a la lista de expectedCars
						//content().json(mapper.writeValueAsString(expectedCars))
						);
	}

	@Test
	void it_should_return_a_car_when_id_exists_in_request() throws Exception {
		// Especificamos el use case
		List<Car> cars = repository.getCars();
		Car newCar = new Car(new CarId(UUID.randomUUID().toString()),new CarTitle("Chevrolet Camaro SS"), new CarDescription("Muscle car americano"),
				new CarBrand("Chevrolet"), new CarPrice(55000.0),
				new CarUrl("https://example.com/chevrolet-camaro-ss.jpg"),
				new CarUrl("https://example.com/chevrolet-camaro-ss-listing"));
		// lo añadimos al repo
		repository.addCar(newCar);
		// When
		when(repository.getCar(newCar.getIdValue())).thenReturn(newCar);
		// Endpoint
		mockMvc.perform(get("/cars/{id}", newCar.getIdValue()).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				//.andExpect(content().json(mapper.writeValueAsString(newCar)
				//))
				;
	}

	@Test
	void it_should_return_not_found_if_id_does_not_exist() throws Exception{
		// When
		when(repository.getCar("fakeId")).thenReturn(null);
		// Endpoint
		mockMvc.perform(
				get("/cars/{id}", "fakeId").accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpectAll(
				status().isNotFound()
				);
	}

}
