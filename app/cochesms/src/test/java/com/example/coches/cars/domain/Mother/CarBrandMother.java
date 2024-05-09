package com.example.coches.cars.domain.Mother;

import com.example.coches.cars.domain.car.CarBrand;
import com.github.javafaker.Faker;

final public class CarBrandMother {
	
	public static CarBrand random() {
		return new CarBrand(new Faker().company().name());
	}
	
	public static CarBrand create(String value) {
		return new CarBrand(value);
	}
}
