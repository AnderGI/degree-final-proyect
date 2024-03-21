package com.example.coches.cars.domain.convertCarToObjectNode;

import com.example.coches.cars.domain.car.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CarImageURLToObjectNodeConverter implements CarFieldToObjectNodeConverter{

	@Override
	public ObjectNode convertCarFieltToObjectNode(Car car, ObjectMapper mapper) {
		// Crear un objeto ObjectNode para el campo 'image url'
		ObjectNode imageNode = mapper.createObjectNode();
		imageNode.put("value", car.getCarImageUrlValue());
		return imageNode;
	}

}
