package com.example.coches.cars.domain.Mother;

import java.util.Locale;
import java.util.UUID;

import com.example.coches.cars.domain.car.CarUrl;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import jakarta.validation.constraints.Pattern;

final public class CarUrlMother {
	public static CarUrl random() {
		return new CarUrl(new Faker().internet().url());
	}
	public static CarUrl create(String value) {
		return new CarUrl(value);
	}
	
}
