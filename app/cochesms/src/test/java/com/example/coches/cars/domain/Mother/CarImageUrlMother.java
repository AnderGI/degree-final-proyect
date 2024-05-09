package com.example.coches.cars.domain.Mother;

import com.example.coches.cars.domain.car.CarUrl;
import com.github.javafaker.Faker;

final public class CarImageUrlMother {
	public static CarUrl random() {
		return new CarUrl(new Faker().internet().image());
	}
	
	public static CarUrl create(String value) {
		return new CarUrl(value);
	}
	
}
