package com.example.coches.cars.domain.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CarDTOImageURLValidator implements CarDTOFieldValidator {

	@Override
	public String validateCarDTOField(CarDTO carDto, ObjectMapper objMapper) {
		String carImageURL = null;
		JsonNode jsonNode = null;
		try {
			if (carDto.getCarImageURL() != null) {

				jsonNode = objMapper.readTree(carDto.getCarImageURL());
		        JsonNode valueNode = jsonNode.get("value");
	            if (valueNode != null) {
	            	carImageURL = valueNode.asText();
	            }
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return carImageURL;
	}

}
