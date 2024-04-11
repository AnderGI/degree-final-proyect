package com.example.coches.cars.domain.convert_car_to_object_node;

import com.example.coches.cars.domain.car.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CarBrandToObjectNodeConverter implements CarFieldToObjectNodeConverter {

	@Override
	public ObjectNode convertCarFieltToObjectNode(Car car, ObjectMapper mapper) {
		// TODO Auto-generated method stub
		ObjectNode brandNode = mapper.createObjectNode();
		brandNode.put("value", car.getBrandValue());
		return brandNode;
	}

}
