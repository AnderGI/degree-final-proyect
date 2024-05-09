package com.example.coches.cars.domain.Mother;

import com.github.javafaker.Faker;

final public class CarStubImageUrl {
	private String value;
	
	public CarStubImageUrl(String value) {
		this.value = value;
	}
	
	public static CarStubImageUrl random() {
		return new CarStubImageUrl(new Faker().internet().image());
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
