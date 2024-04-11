package com.example.coches.cars.domain.convert_car_to_object_node;

import com.example.coches.cars.domain.car.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

final public class CarTitleToObjectNodeConverter implements CarFieldToObjectNodeConverter {

	@Override
	public ObjectNode convertCarFieltToObjectNode(Car car, ObjectMapper mapper) {
		// Crear un objeto ObjectNode para el campo 'title'
		ObjectNode titleNode = mapper.createObjectNode();
		titleNode.put("value", car.getTitleValue());
		return titleNode;
	}

}
