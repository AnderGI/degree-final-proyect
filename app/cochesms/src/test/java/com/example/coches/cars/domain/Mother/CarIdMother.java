package com.example.coches.cars.domain.Mother;

import java.util.UUID;

import com.example.coches.cars.domain.car.CarId;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

final public class CarIdMother {	
	public static CarId random() {
		return new CarId(UUID.randomUUID().toString());
	}
	
	public static CarId create(String value) {
		return new CarId(value);
	}
	
}
