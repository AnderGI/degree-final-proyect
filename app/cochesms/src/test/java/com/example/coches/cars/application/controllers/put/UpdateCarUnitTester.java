package com.example.coches.cars.application.controllers.put;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.coches.cars.application.controllers.CarPutController;
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
public class UpdateCarUnitTester {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CarPutController getController;
	@Autowired
	private ObjectMapper mapper;
	// Mockeamos una instancia del bean CarRepository para no utilizar el mismo
	@MockBean
	private CarRepository repository;
	
	@Test
	void it_should_update_an_existing_car() throws Exception{
		// Configuracion de un coche ya actualizado
	    Car updatedCar =  new Car(new CarId(UUID.randomUUID().toString()), 
	    		new CarTitle("ACTUALIZADO"), 
	            new CarDescription("ACTUALIZADO"), 
	            new CarBrand("BMW"), new CarPrice(70000.0), 
	            new CarUrl("https://example.com/bmw-m3.jpg"), 
	            new CarUrl("https://example.com/bmw-m3-listing"));
	    // Convertir el ObjectNode a una cadena JSON
        String jsonString = CarToJsonConverter
        		.convert(updatedCar, mapper).toString();
	    // Especificamos lo que tiene que pasar cuando se haga el update
	    when(repository.updateCar(any(Car.class), eq(updatedCar.getIdValue())))
	    .thenReturn(updatedCar);
	    
	    // Ejecutamos el put endpoint
	    
	    mockMvc.perform(
	    		put("/cars/{id}", updatedCar.getIdValue())
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(jsonString)
	    )
	    .andDo(print())
	    .andExpectAll(status().isOk());
	}
	
	@Test
	void it_should_return_null_when_inexistent_car() throws Exception{
		String fakeId = "aaaaaaaaaaaaa";
	    Car updatedCar =  new Car(new CarId(UUID.randomUUID().toString()), 
	    		new CarTitle("ACTUALIZADO"), 
	            new CarDescription("ACTUALIZADO"), 
	            new CarBrand("BMW"), new CarPrice(70000.0), 
	            new CarUrl("https://example.com/bmw-m3.jpg"), 
	            new CarUrl("https://example.com/bmw-m3-listing"));
	    // Convertir el ObjectNode a una cadena JSON
        String jsonString = CarToJsonConverter
        		.convert(updatedCar, mapper).toString();
		
		when(repository.updateCar(any(Car.class), eq(fakeId))).thenReturn(null);
		
		mockMvc.perform(
				put("/cars/{id}", fakeId)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(CarToJsonConverter.convert(updatedCar, mapper).toString())
		).andDo(print())
		.andExpectAll(
				status().isNotFound()
		);
		
	}
}
