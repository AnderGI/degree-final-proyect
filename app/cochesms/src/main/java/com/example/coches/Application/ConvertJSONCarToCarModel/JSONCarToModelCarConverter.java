package com.example.coches.Application.ConvertJSONCarToCarModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.coches.Domain.Entities.Car;
import com.example.coches.Domain.Entities.CarJSONModel;
import com.example.coches.Domain.Repositories.CarRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

final public class JSONCarToModelCarConverter {
	
	@Autowired
	private static CarRepository repo;
	

	public static List<Car> convertJSONCarToModelCarFromJSONString(String messageString) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();

		CarJSONModel[] cars = objectMapper.readValue(messageString, CarJSONModel[].class);
		List<Car> carsList = new ArrayList();
		for (CarJSONModel carModel : cars) {
			// System.out.println("Título del coche: " + car.getTitleValue());
			carsList.add(new Car(carModel.getTitle(), carModel.getDescription(), carModel.getBrand(),
					carModel.getPrice(), carModel.getCarImageURL(), carModel.getCarAnnouncementUrl()));
		}
		
		return carsList;
	}
}
