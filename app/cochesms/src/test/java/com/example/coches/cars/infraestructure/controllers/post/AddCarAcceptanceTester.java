package com.example.coches.cars.infraestructure.controllers.post;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

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
import com.example.coches.cars.infraestructure.controllers.CarPostController;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class AddCarAcceptanceTester {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CarPostController postController;
	@Autowired
	private ObjectMapper mapper;
	// Mockeamos una instancia del bean CarRepository para no utilizar el mismo
	@MockBean
	private CarRepository repository;

	@Test
	void it_should_return_a_car_when_car_is_added() throws Exception {
		Car toAddCar = new Car(new CarId(UUID.randomUUID().toString()), new CarTitle("BMW M3"),
				new CarDescription("Sedán deportivo de lujo"), new CarBrand("BMW"), new CarPrice(70000.0),
				new CarUrl("https://example.com/bmw-m3.jpg"), new CarUrl("https://example.com/bmw-m3-listing"));
		String carToJson = CarToJsonConverter.convert(toAddCar, mapper).toString();

		// Mocking
		when(repository.addCar(toAddCar)).thenReturn(toAddCar);

		// Ejecutar el endpoint
		mockMvc.perform(post("/cars").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(carToJson)).andDo(print()).andExpectAll(status().isCreated(),
						header().string("Location", "/cars/" + toAddCar.getIdValue()), content().json(carToJson));

	}

	@Test
	void it_should_add_a_new_car_if_car_info_is_correct() throws Exception {
		Car toAddCar = new Car(new CarId(UUID.randomUUID().toString()), new CarTitle("BMW M3"),
				new CarDescription("Sedán deportivo de lujo"), new CarBrand("BMW"), new CarPrice(70000.0),
				new CarUrl("https://example.com/bmw-m3.jpg"), new CarUrl("https://example.com/bmw-m3-listing"));

		// Convertir el ObjectNode a una cadena JSON
		String jsonString = CarToJsonConverter.convert(toAddCar, mapper).toString();

		// When
		when(repository.addCar(toAddCar)).thenReturn(toAddCar);

		// Ejecutar el endpoint
		mockMvc.perform(
				post("/cars")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andDo(print())
				.andExpectAll(
						status().isCreated(), 
						header().string("Location", "/cars/" + toAddCar.getIdValue()));
	}

	@Test
	void it_should_not_add_a_new_car_if_car_info_is_incorrect() throws Exception {
		// Precio es cero y url de imagen esta vacia
		Car toAddCar = new Car(new CarId(UUID.randomUUID().toString()), new CarTitle("BMW M3"),
				new CarDescription("Sedán deportivo de lujo"), new CarBrand("BMW"), new CarPrice(0.0), new CarUrl(""),
				new CarUrl("https://example.com/bmw-m3-listing"));
		String jsonString = CarToJsonConverter.convert(toAddCar, mapper).toString();
		// when
		when(repository.addCar(toAddCar)).thenReturn(null);
		
		
		// Endpoint
		mockMvc.perform(
				post("/cars")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(jsonString)
		).andDo(print())
		.andExpect(
				status().isBadRequest()
		);
	}

}
