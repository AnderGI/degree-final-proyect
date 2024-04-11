package com.example.coches.cars.domain.convert_car_to_object_node;

import com.example.coches.cars.domain.car.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

final public class CarIdToObjectNodeConverter implements CarFieldToObjectNodeConverter {

	@Override
	public ObjectNode convertCarFieltToObjectNode(Car car, ObjectMapper mapper) {
		// TODO Auto-generated method stub
		// Crear un objeto ObjectNode para el campo 'id'
		ObjectNode idNode = mapper.createObjectNode();
		idNode.put("value", car.getIdValue());
		return idNode;
	}

}
