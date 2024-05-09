package com.example.coches.cars.infraestructure.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.coches.cars.application.add_car.CarSaver;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.convert_car_model_to_json_model.CarToJsonConverter;
import com.example.coches.cars.domain.validate_car.CarValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
final public class CarPostController {
	@Autowired
	private CarRepository repo;
	
	@Autowired
	private ObjectMapper mapper;

	@PostMapping(path = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ObjectNode> addCar(@RequestBody Car car) {
		// Validaciones 
		// Value objects para las propiedades
		// Luego la validación del coche creo que estaría mejor en la capa de aplicación
		// habría que lanzar errores -> errores de dominio y gestionarlos también
		CarSaver saveCarUseCase = new CarSaver(repo);
		try {
			// si lanza excepcion es porque el coche tiene algún campo vacío
			// esa es de momento la validación que tenemos
			saveCarUseCase.save_car(car);
			URI uri = null;
			try {
				uri = new URI("/cars/" + car.getIdValue());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ResponseEntity.created(uri).body(CarToJsonConverter.convert(car, mapper));
		}catch(Exception exp) {
			return ResponseEntity.badRequest().build();
		}
		
		
	}
}