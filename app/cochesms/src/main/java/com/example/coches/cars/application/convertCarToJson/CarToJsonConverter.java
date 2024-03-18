package com.example.coches.cars.application.convertCarToJson;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.coches.cars.domain.car.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

final public class CarToJsonConverter {

	public static ObjectNode convert_car_to_json(Car car, ObjectMapper mapper) {
		// Crear un objeto ObjectNode para construir el JSON
		ObjectNode jsonNode = mapper.createObjectNode();

		// Crear un objeto ObjectNode para el campo 'id'
		ObjectNode idNode = mapper.createObjectNode();
		idNode.put("value", car.getIdValue());
		jsonNode.set("id", idNode);

		// Crear un objeto ObjectNode para el campo 'title'
		ObjectNode titleNode = mapper.createObjectNode();
		titleNode.put("value", car.getTitleValue());
		jsonNode.set("title", titleNode);

		// Crear un objeto ObjectNode para el campo 'description'
		ObjectNode descriptionNode = mapper.createObjectNode();
		descriptionNode.put("value", car.getDescriptionValue());
		jsonNode.set("description", descriptionNode);

		// Crear un objeto ObjectNode para el campo 'brand'
		ObjectNode brandNode = mapper.createObjectNode();
		brandNode.put("value", car.getBrandValue());
		jsonNode.set("brand", brandNode);

		// Crear un objeto ObjectNode para el campo 'price'
		ObjectNode priceNode = mapper.createObjectNode();
		priceNode.put("value", car.getCarPriceValue());
		jsonNode.set("price", priceNode);

		// Crear un objeto ObjectNode para el campo 'image url'
		ObjectNode imageNode = mapper.createObjectNode();
		imageNode.put("value", car.getCarImageUrlValue());
		jsonNode.set("carImageURL", imageNode);

		// Crear un objeto ObjectNode para el campo 'announcment url'
		ObjectNode announcementNode = mapper.createObjectNode();
		announcementNode.put("value", car.getCarAnnouncmentURLValue());
		jsonNode.set("carAnnouncementURL", announcementNode);
		
		return jsonNode;
	}
}
