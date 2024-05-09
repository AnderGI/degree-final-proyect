package com.example.coches.cars.infraestructure.controllers.get.get_all_car_brands;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
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
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class GetAllCarBrandsAcceptanceTester {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CarGetController getController;
	@Autowired
	private ObjectMapper mapper;
	@MockBean
	private CarRepository repository;
	
	@Test
	void it_should_retreive_all_car_brands_as_a_string_list() 
	throws Exception{
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
		expectedCars.forEach(car -> repository.addCar(car));
		
		List<String> expectedBrands = expectedCars.stream().map(car -> car.getBrandValue()).toList();
		
		when(repository.getAllBrands()).thenReturn(expectedBrands);
		
		mockMvc.perform(get("/cars/brands")).andDo(print())
		.andExpectAll(
				status().isOk(),
				content().contentType(MediaType.APPLICATION_JSON_VALUE),
				content().json(mapper.writeValueAsString(expectedBrands))
		);
		
	
	}
}
