package com.example.coches.cars.domain.Mother;

import com.github.javafaker.Faker;
import com.github.javafaker.Lorem;

final public class CarStubTitle {
	private String value;
	
	public CarStubTitle(String value) {
		this.value = value;
	}
	
	public static CarStubTitle random() {
		return new CarStubTitle(new Faker().lorem().characters(25, true));
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
