package com.example.coches.cars.domain.Mother;

import com.example.coches.cars.domain.car.CarTitle;
import com.github.javafaker.Faker;
import com.github.javafaker.Lorem;

final public class CarTitleMother {
	public static CarTitle random() {
		return new CarTitle(new Faker().lorem().characters(25, true));
	}
	public static CarTitle create(String value) {
		return new CarTitle(value);
	}

}
