package com.example.coches.cars.domain.car;

import java.util.UUID;

final public class CarId {
	private String value;
	
	public CarId() {
		this.value = UUID.randomUUID().toString();
	}
	
	public String getCarIdValue() {
		return value;
	}
}
