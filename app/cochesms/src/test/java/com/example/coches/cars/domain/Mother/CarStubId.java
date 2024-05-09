package com.example.coches.cars.domain.Mother;

import java.util.UUID;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

final public class CarStubId {
	private String value;
	public CarStubId(String value) {
		this.value = value;
	}
	
	public static CarStubId random() {
		return new CarStubId(UUID.randomUUID().toString());
	}
	


	@Override
	public String toString() {
		return value;
	}
	
	
}
