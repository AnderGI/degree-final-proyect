package com.example.coches.cars.application.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@WebMvcTest
public class CarPostControllerTest {
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
	void it_should_add_a_new_car_if_car_info_is_correct() throws Exception {
	    Car toAddCar =  new Car(new CarTitle("BMW M3"), 
	            new CarDescription("Sedán deportivo de lujo"), 
	            new CarBrand("BMW"), new CarPrice(70000.0), 
	            new CarUrl("https://example.com/bmw-m3.jpg"), 
	            new CarUrl("https://example.com/bmw-m3-listing"));
	    

        // Crear un objeto ObjectNode para construir el JSON
        ObjectNode jsonNode = mapper.createObjectNode();

        // Crear un objeto ObjectNode para el campo 'id'
        ObjectNode idNode = mapper.createObjectNode();
        idNode.put("value", toAddCar.getIdValue());
        jsonNode.set("id", idNode);
        
        // Crear un objeto ObjectNode para el campo 'title'
        ObjectNode titleNode = mapper.createObjectNode();
        titleNode.put("value", toAddCar.getTitleValue());
        jsonNode.set("title", titleNode);
        
        // Crear un objeto ObjectNode para el campo 'description'
        ObjectNode descriptionNode = mapper.createObjectNode();
        descriptionNode.put("value", toAddCar.getDescriptionValue());
        jsonNode.set("description", descriptionNode);
	    
        // Crear un objeto ObjectNode para el campo 'brand'
        ObjectNode brandNode = mapper.createObjectNode();
        brandNode.put("value", toAddCar.getBrandValue());
        jsonNode.set("brand", brandNode);
        
        // Crear un objeto ObjectNode para el campo 'price'
        ObjectNode priceNode = mapper.createObjectNode();
        priceNode.put("value", toAddCar.getCarPriceValue());
        jsonNode.set("price", priceNode);
        
        // Crear un objeto ObjectNode para el campo 'image url'
        ObjectNode imageNode = mapper.createObjectNode();
        imageNode.put("value", toAddCar.getCarImageUrlValue());
        jsonNode.set("carImageURL", imageNode);
        
        // Crear un objeto ObjectNode para el campo 'announcment url'
        ObjectNode announcmentNode = mapper.createObjectNode();
        announcmentNode.put("value", toAddCar.getCarAnnouncmentURLValue());
        jsonNode.set("carAnnouncementURL", announcmentNode);
  
        
        // Convertir el ObjectNode a una cadena JSON
        String jsonString = jsonNode.toString();
	    
	    // When
	    when(repository.addCar(toAddCar)).thenReturn(toAddCar);
	    
	    // Ejecutar el endpoint
	    mockMvc.perform(
	            post("/cars", jsonString) 
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(jsonString)
	    )
	    .andDo(print())
	    .andExpectAll(
	            status().isCreated(),
	            header().string("Location", "/cars/" + toAddCar.getIdValue())
	    );
	}

}
