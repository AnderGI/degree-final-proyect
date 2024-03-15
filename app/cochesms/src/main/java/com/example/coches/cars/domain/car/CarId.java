package com.example.coches.cars.domain.car;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

final public class CarId {
	private String value;
	
	public CarId() {
		this.value = UUID.randomUUID().toString();
	}
	
	public String getCarIdValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
