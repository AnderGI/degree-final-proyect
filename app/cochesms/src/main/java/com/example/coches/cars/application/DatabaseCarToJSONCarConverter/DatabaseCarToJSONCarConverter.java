package com.example.coches.cars.application.DatabaseCarToJSONCarConverter;

import java.util.ArrayList;
import java.util.List;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDTO;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

final public class DatabaseCarToJSONCarConverter {
	static public List<Car> convert_database_cars_to_json_response_cars(List<CarDTO> carsDTO){
		List<Car> cars = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(CarDTO carDTO : carsDTO) {
	        // ID
	        JsonNode jsonNode = null;
			try {
				jsonNode = objectMapper.readTree(carDTO.getId());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        // Obtener el valor "value"
	        String id = jsonNode.get("value").asText();
	        
	        
			// Title
			try {
				jsonNode = objectMapper.readTree(carDTO.getTitle());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String title = jsonNode.get("value").asText();
	        
	        
			//Description
	        try {
				jsonNode = objectMapper.readTree(carDTO.getDescription());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String description = jsonNode.get("value").asText();
	        
	        
			// Brand
	        try {
				jsonNode = objectMapper.readTree(carDTO.getBrand());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String brand = jsonNode.get("value").asText();
			
			
	        // Image url
	        try {
				jsonNode = objectMapper.readTree(carDTO.getCarImageURL());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String carImageURL = jsonNode.get("value").asText();
	       
	        
			// Announcement URL
	        try {
				jsonNode = objectMapper.readTree(carDTO.getCarAnnouncementUrl());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String carAnnouncementURL = jsonNode.get("value").asText();
			
	        // Precio
	        try {
				jsonNode = objectMapper.readTree(carDTO.getPrice());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String price = jsonNode.get("value").asText();
	        
	        Car newCar = new Car(
	        		new CarTitle(title), 
	        		new CarDescription(description), 
	        		new CarBrand(brand), 
	        		new CarPrice(price), 
	        		new CarUrl(carImageURL), 
	        		new CarUrl(carAnnouncementURL));
	        cars.add(newCar);
		}
		return cars;
	}
}
