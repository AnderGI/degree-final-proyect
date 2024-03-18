package com.example.coches.cars.application.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.messagereceiver.MessageReceiver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;



@RestController
final public class CarGetController {
	@Autowired
	private CarRepository repo;
	@Autowired
	private ObjectMapper mapper;
	@GetMapping("/cars")
	public List<ObjectNode> getCars(){
		List<ObjectNode> nodes = new ArrayList<>();
				for(Car car: repo.getCars()) {
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
		    nodes.add(jsonNode);
		}
		// return 
		return nodes;
	}
	@GetMapping("/cars/{id}")
	public ResponseEntity<ObjectNode> getCar(@PathVariable String id) {
	    Car requestedCar = repo.getCar(id);
	    
	    if (requestedCar == null) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    // Crear un objeto ObjectNode para construir el JSON
	    ObjectNode jsonNode = mapper.createObjectNode();

	    // Crear un objeto ObjectNode para el campo 'id'
	    ObjectNode idNode = mapper.createObjectNode();
	    idNode.put("value", requestedCar.getIdValue());
	    jsonNode.set("id", idNode);
	    
	    // Crear un objeto ObjectNode para el campo 'title'
	    ObjectNode titleNode = mapper.createObjectNode();
	    titleNode.put("value", requestedCar.getTitleValue());
	    jsonNode.set("title", titleNode);
	    
	    // Crear un objeto ObjectNode para el campo 'description'
	    ObjectNode descriptionNode = mapper.createObjectNode();
	    descriptionNode.put("value", requestedCar.getDescriptionValue());
	    jsonNode.set("description", descriptionNode);
	    
	    // Crear un objeto ObjectNode para el campo 'brand'
	    ObjectNode brandNode = mapper.createObjectNode();
	    brandNode.put("value", requestedCar.getBrandValue());
	    jsonNode.set("brand", brandNode);
	    
	    // Crear un objeto ObjectNode para el campo 'price'
	    ObjectNode priceNode = mapper.createObjectNode();
	    priceNode.put("value", requestedCar.getCarPriceValue());
	    jsonNode.set("price", priceNode);
	    
	    // Crear un objeto ObjectNode para el campo 'image url'
	    ObjectNode imageNode = mapper.createObjectNode();
	    imageNode.put("value", requestedCar.getCarImageUrlValue());
	    jsonNode.set("carImageURL", imageNode);
	    
	    // Crear un objeto ObjectNode para el campo 'announcment url'
	    ObjectNode announcementNode = mapper.createObjectNode();
	    announcementNode.put("value", requestedCar.getCarAnnouncmentURLValue());
	    jsonNode.set("carAnnouncementURL", announcementNode);
	    
	    return ResponseEntity.ok(jsonNode);
	}

}
