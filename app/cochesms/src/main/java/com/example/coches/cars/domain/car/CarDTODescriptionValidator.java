package com.example.coches.cars.domain.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CarDTODescriptionValidator implements CarDTOFieldValidator {

	@Override
	public String validateCarDTOField(CarDTO carDto, ObjectMapper objMapper) {
		JsonNode jsonNode = null;
		String description = null;
		try {
			if (carDto.getDescription() != null) {
				jsonNode = objMapper.readTree(carDto.getDescription());
				
	            JsonNode valueNode = jsonNode.get("value");
	            if (valueNode != null) {
	            	description = valueNode.asText();
	            }
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return description;
	}

}
