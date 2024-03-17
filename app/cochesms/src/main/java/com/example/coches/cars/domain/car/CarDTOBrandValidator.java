package com.example.coches.cars.domain.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CarDTOBrandValidator implements CarDTOFieldValidator {

	@Override
	public String validateCarDTOField(CarDTO carDto, ObjectMapper objMapper) {
		String brand = null;
		JsonNode jsonNode = null;
		try {
			if (carDto.getBrand() != null) {
				jsonNode = objMapper.readTree(carDto.getBrand());
				JsonNode valueNode = jsonNode.get("value");
	            if (valueNode != null) {
	            	brand = valueNode.asText();
	            }
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return brand;
	}

}
