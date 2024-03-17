package com.example.coches.cars.domain.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CarDTOPriceValidator implements CarDTOFieldValidator {

	@Override
	public String validateCarDTOField(CarDTO carDto, ObjectMapper objMapper) {
		String price = null;
		JsonNode jsonNode = null;
		try {
			if (carDto.getPrice() != null) {
				jsonNode = objMapper.readTree(carDto.getPrice());
		        JsonNode valueNode = jsonNode.get("value");
	            if (valueNode != null) {
	            	price = valueNode.asText();
	            }
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return price;
	}

}
