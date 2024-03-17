package com.example.coches.cars.application.DatabaseCarToJSONCarConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDTO;
import com.example.coches.cars.domain.car.CarDTOAnnouncmentImageURLValidator;
import com.example.coches.cars.domain.car.CarDTOBrandValidator;
import com.example.coches.cars.domain.car.CarDTODescriptionValidator;
import com.example.coches.cars.domain.car.CarDTOFieldValidator;
import com.example.coches.cars.domain.car.CarDTOImageURLValidator;
import com.example.coches.cars.domain.car.CarDTOPriceValidator;
import com.example.coches.cars.domain.car.CarDTOTitleValidator;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

final public class DatabaseCarToJSONCarConverter {

	static public List<Car> convert_database_cars_to_json_response_cars(List<CarDTO> carsDTO) {
		List<Car> cars = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		// Mantener el orden de los validadores con el orden
		// De los parametros pasados por constructor
		List<CarDTOFieldValidator> fieldValidators = Arrays.asList(
				new CarDTOTitleValidator(),
				new CarDTODescriptionValidator(), 
				new CarDTOBrandValidator(), 
				new CarDTOPriceValidator(),
				new CarDTOImageURLValidator(), 
				new CarDTOAnnouncmentImageURLValidator());
		for (CarDTO carDTO : carsDTO) {

			// Por cada dto validar los campos del dto y devolver un array con ello
			List<String> validatedFields = fieldValidators.stream()
			.map(validator -> validator.validateCarDTOField(carDTO, objectMapper))
			.toList();
			
			// Crear el car en base a los campos de validatedFields
			// IMPORTANTE mantener el ORDEN
			Car newCar = new Car(
					new CarTitle(validatedFields.get(0)), 
					new CarDescription(validatedFields.get(1)), 
					new CarBrand(validatedFields.get(2)),
					new CarPrice(null), 
					new CarUrl(validatedFields.get(4)), 
					new CarUrl(validatedFields.get(5)));
			cars.add(newCar);

		}
		return cars;
	}
}
