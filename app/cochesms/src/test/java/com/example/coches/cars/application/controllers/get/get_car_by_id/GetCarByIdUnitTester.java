package com.example.coches.cars.application.controllers.get.get_car_by_id;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.coches.cars.application.controllers.CarGetController;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarId;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.example.coches.cars.domain.convert_car_model_to_json_model.CarToJsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;

// En los tests unitarios (Application + Domain)
// falseamos las implementaciones de infraestructura
@WebMvcTest
public class GetCarByIdUnitTester {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CarGetController getController;
	@Autowired
	private ObjectMapper mapper;
	@MockBean
	private CarRepository repository;

	// Testear endpoint -> /cars/{id}
	// Casos : Que el id del coche exista o que no exista
	@Test
	void it_should_return_an_specific_car_based_on_existing_car_id() throws Exception {

		Car addedCar = new Car(new CarId(UUID.randomUUID().toString()), new CarTitle("Chevrolet Camaro SS"),
				new CarDescription("Muscle car americano"), new CarBrand("Chevrolet"), new CarPrice(55000.0),
				new CarUrl("https://example.com/chevrolet-camaro-ss.jpg"),
				new CarUrl("https://example.com/chevrolet-camaro-ss-listing"));
		repository.addCar(addedCar);
		String carId = addedCar.getIdValue();
		String addedCarToJson = CarToJsonConverter.convert(addedCar, mapper).toString();
		
		when(repository.getCar(carId)).thenReturn(addedCar);

		// Endpoint
		mockMvc.perform(get("/cars/{id}", carId))
		.andDo(print())
		.andExpectAll(
				status().isOk(), 
				content().contentType(MediaType.APPLICATION_JSON),
				content().json(addedCarToJson)
		);
	}

	@Test
	void it_should_not_return_car_when_car_id_does_not_exist() throws Exception {
		String fakeId = "aaaaaaaaaaaaaaaaaaaaaaaaa";
		// When
		when(repository.getCar(fakeId)).thenReturn(null);
		// Endpoint
		mockMvc.perform(get("/cars/{id}", fakeId))
		.andDo(print())
		.andExpectAll(
				status().isNotFound()
		);
	}

}
