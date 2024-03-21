package com.example.coches.cars.application.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
public class CarDeleteControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CarRepository repository;
	@Autowired
	private CarDeleteController deleteController;
	@Autowired
	private ObjectMapper mapper;

	@Test
	void it_should_delete_an_existing_car_from_car_id_path_variable() throws Exception {
/*		 Having
		// arraylist vacia
		Car toAddCar1 = new Car(new CarId(UUID.randomUUID().toString()), new CarTitle("BMW M3"),
				new CarDescription("Sedán deportivo de lujo"), new CarBrand("BMW"), new CarPrice(70000.0),
				new CarUrl("https://example.com/bmw-m3.jpg"), new CarUrl("https://example.com/bmw-m3-listing"));
		Car toAddCar2 = new Car(new CarId(UUID.randomUUID().toString()), new CarTitle("Mercedes clase E W212"),
				new CarDescription("Coche clásico de lujo"), new CarBrand("mercedes"), new CarPrice(7000.0),
				new CarUrl("https://example.com/bmw-m3.jpg"), new CarUrl("https://example.com/bmw-m3-listing"));
		repository.addCar(toAddCar1);
		repository.addCar(toAddCar2);
		String jsonString = CarToJsonConverter
				.convert_car_to_json(toAddCar1, mapper).toString();
		// When
		when(repository.deleteCar(toAddCar1.getIdValue())).thenReturn(toAddCar1);
		// Endpoint
		mockMvc.perform(delete("/cars/{id}", toAddCar1.getIdValue())).andDo(print()).andExpectAll(status().isOk(),
				content().contentType(MediaType.APPLICATION_JSON), content().json(jsonString));
*/	
		Car car1 = new Car(new CarId(UUID.randomUUID().toString()), new CarTitle("BMW M3"),
				new CarDescription("Sedán deportivo de lujo"), new CarBrand("BMW"), new CarPrice(70000.0),
				new CarUrl("https://example.com/bmw-m3.jpg"), new CarUrl("https://example.com/bmw-m3-listing"));
		String jsonString = CarToJsonConverter
				.convert_car_to_json(car1, mapper).toString();
		mockMvc.perform(
	            post("/cars") 
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(jsonString)
	    );
		
		
		mockMvc.perform(delete("/cars/{id}",repository.getCars().get(0).getIdValue()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
}
