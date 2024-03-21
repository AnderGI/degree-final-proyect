package com.example.coches.cars.domain.convertCarToObjectNode;

import com.example.coches.cars.domain.car.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Con el uso de una instancia de ObjectMapper se crea un nodo
 * que seria una representacion de un nodo de JSON 
 * cada implementación de esta interfaz creará un nodo con el valor correspondiente
 * De esta forma se mantiene la estructura que habrá en los value objects
 * 
 * */
public interface CarFieldToObjectNodeConverter {
	ObjectNode convertCarFieltToObjectNode(Car car, ObjectMapper mapper);
}
