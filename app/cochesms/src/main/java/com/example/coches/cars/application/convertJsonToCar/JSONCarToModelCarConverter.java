package com.example.coches.cars.application.convertJsonToCar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarDTO;
import com.example.coches.cars.domain.car.CarRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


final public class JSONCarToModelCarConverter {
	
	
	private CarRepository repo;
	
	public JSONCarToModelCarConverter(CarRepository repo) {
		this.repo = repo;
	}

	public void convertJSONCarToModelCarFromJSONString(String messageString) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();

		CarDTO[] cars = objectMapper.readValue(messageString, CarDTO[].class);
		//List<Car> carsList = new ArrayList();
		for (CarDTO carModel : cars) {
			Car car = new Car(carModel.getTitle(), carModel.getDescription(), carModel.getBrand(), carModel.getPrice(), carModel.getCarImageURL(),carModel.getCarAnnouncementUrl());
			repo.addCar(car);
			//carsList.add(car);
		}
		
		
		
	}
}
