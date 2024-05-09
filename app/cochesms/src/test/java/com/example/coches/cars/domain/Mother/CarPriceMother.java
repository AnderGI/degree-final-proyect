package com.example.coches.cars.domain.Mother;

import java.text.NumberFormat;

import com.example.coches.cars.domain.car.CarPrice;
import com.github.javafaker.Faker;

final public class CarPriceMother {
	public static CarPrice random() {
		return new CarPrice(new Faker().number().randomDouble(3,20000, 200000));
	}
	public static CarPrice create(Double value) {
		return new CarPrice(value);
	}
}
