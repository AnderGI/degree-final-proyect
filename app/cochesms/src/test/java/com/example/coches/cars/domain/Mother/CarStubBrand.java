package com.example.coches.cars.domain.Mother;

import com.github.javafaker.Faker;

final public class CarStubBrand {
	private String value;
	public CarStubBrand(String value) {
		this.value = value;
	}
	
	public static CarStubBrand random() {
		return new CarStubBrand(new Faker().internet().url());
	}
	


	@Override
	public String toString() {
		return value;
	}
}
