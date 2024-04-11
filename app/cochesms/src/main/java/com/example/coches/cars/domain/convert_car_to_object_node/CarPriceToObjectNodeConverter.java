package com.example.coches.cars.domain.convert_car_to_object_node;

import com.example.coches.cars.domain.car.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CarPriceToObjectNodeConverter implements CarFieldToObjectNodeConverter {

	@Override
	public ObjectNode convertCarFieltToObjectNode(Car car, ObjectMapper mapper) {
		ObjectNode priceNode = mapper.createObjectNode();
		priceNode.put("value", car.getCarPriceValue());
		return priceNode;
	}

}
