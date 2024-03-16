package com.example.coches.cars.application.convertJsonToCar;

import com.example.coches.cars.application.createCar.CarCreator;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDTO;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarId;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
// RABBIT
final public class JSONCarToModelCarConverter {

	public JSONCarToModelCarConverter() {
		
	}

	public CarDTO[] convertJSONCarToModelCarFromJSONString(String messageString)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();

		CarDTO[] cars = objectMapper.readValue(messageString, CarDTO[].class);
		return cars;

	}
}
