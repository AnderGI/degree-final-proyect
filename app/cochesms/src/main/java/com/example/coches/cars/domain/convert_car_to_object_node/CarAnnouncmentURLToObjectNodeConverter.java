package com.example.coches.cars.domain.convert_car_to_object_node;

import com.example.coches.cars.domain.car.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CarAnnouncmentURLToObjectNodeConverter implements CarFieldToObjectNodeConverter {

	@Override
	public ObjectNode convertCarFieltToObjectNode(Car car, ObjectMapper mapper) {
		// Crear un objeto ObjectNode para el campo 'announcment url'
		ObjectNode announcementNode = mapper.createObjectNode();
		announcementNode.put("value", car.getCarAnnouncmentURLValue());
		return announcementNode;
	}

}
