package com.example.coches.cars.application.controllers.delete;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.coches.cars.application.controllers.CarDeleteController;
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

@WebMvcTest
public class DeleteCarUnitTester {
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
		Car car1 = new Car(new CarId(UUID.randomUUID().toString()), new CarTitle("BMW M3"),
				new CarDescription("Sedán deportivo de lujo"), new CarBrand("BMW"), new CarPrice(70000.0),
				new CarUrl("https://example.com/bmw-m3.jpg"), new CarUrl("https://example.com/bmw-m3-listing"));
		
		String jsonString = CarToJsonConverter
				.convert(car1, mapper).toString();
		
		  // Simular el comportamiento del repositorio para devolver el coche cuando se llame al método getCar
        when(repository.getCar(car1.getIdValue())).thenReturn(car1);
        
        // Simular el comportamiento del repositorio para devolver el coche eliminado cuando se llame al método deleteCar
        when(repository.deleteCar(car1.getIdValue())).thenReturn(car1);
        
        // Ejecutamos el endpoint
        mockMvc
        .perform(
        		delete("/cars/{id}", car1.getIdValue()
        )
        .contentType(MediaType.APPLICATION_JSON))
        .andExpectAll(status().isOk());
    
	}
	
	@Test
	void it_should_return_not_not_found_for_deleting_a_with_non_existing_id() throws Exception{
		// Especificamos el fake id
		String fakeCarId = "hhhhhhhhhhh";
		
		// Especificamos el mockeo
		when(repository.deleteCar(fakeCarId)).thenReturn(null);
		
		// Ejecutamos el endpoint
		mockMvc.perform(
				delete("/cars/{id}", fakeCarId)
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andDo(print())
		.andExpectAll(
				status().isNotFound()
		);
		
	}
}
