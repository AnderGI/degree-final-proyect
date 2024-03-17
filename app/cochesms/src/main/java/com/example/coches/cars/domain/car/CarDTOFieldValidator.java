package com.example.coches.cars.domain.car;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface CarDTOFieldValidator {
	String validateCarDTOField(CarDTO carDto, ObjectMapper objMapper);
}
