package com.example.coches.cars.domain.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CarDTOAnnouncmentImageURLValidator implements CarDTOFieldValidator {

	@Override
	public String validateCarDTOField(CarDTO carDto, ObjectMapper objMapper) {
		String carAnnouncementURL = null;
		JsonNode jsonNode= null;
		try {
			if (carDto.getCarAnnouncementUrl() != null) {

				jsonNode = objMapper.readTree(carDto.getCarAnnouncementUrl());
		        JsonNode valueNode = jsonNode.get("value");
	            if (valueNode != null) {
	            	carAnnouncementURL = valueNode.asText();
	            }
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return carAnnouncementURL;
	}

}
