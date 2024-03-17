package com.example.coches.cars.domain.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CarDTOTitleValidator implements CarDTOFieldValidator {

	@Override
	public String validateCarDTOField(CarDTO carDto, ObjectMapper objMapper) {
		// TODO Auto-generated method stub
		JsonNode jsonNode = null;
		String title = null;
		try {
			if (carDto.getTitle() != null) {
				jsonNode = objMapper.readTree(carDto.getTitle());
	            JsonNode valueNode = jsonNode.get("value");
	            if (valueNode != null) {
	            	title = valueNode.asText();
	            }
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return title;
	}

}
