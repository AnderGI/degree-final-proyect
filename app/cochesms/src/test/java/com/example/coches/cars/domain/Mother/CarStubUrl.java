package com.example.coches.cars.domain.Mother;

import java.util.Locale;
import java.util.UUID;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import jakarta.validation.constraints.Pattern;

final public class CarStubUrl {
	private String value;
	public CarStubUrl(String value) {
		this.value = value;
	}
	
	public static CarStubUrl random() {
		return new CarStubUrl(new Faker().internet().url());
	}
	


	@Override
	public String toString() {
		return value;
	}
	
}
