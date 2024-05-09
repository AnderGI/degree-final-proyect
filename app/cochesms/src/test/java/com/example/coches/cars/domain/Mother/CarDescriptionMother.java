package com.example.coches.cars.domain.Mother;

import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.github.javafaker.Faker;

final public class CarDescriptionMother {
	public static CarDescription random() {
		return new CarDescription(new Faker().lorem().paragraph());
	}
	
	public static CarDescription create(String value) {
		return new CarDescription(value);
	}

}
