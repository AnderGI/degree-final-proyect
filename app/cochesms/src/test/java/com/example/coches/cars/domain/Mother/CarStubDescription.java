package com.example.coches.cars.domain.Mother;

import com.github.javafaker.Faker;

final public class CarStubDescription {
	private String value;
	
	public CarStubDescription(String value) {
		this.value = value;
	}
	
	public static CarStubDescription random() {
		return new CarStubDescription(new Faker().lorem().paragraph());
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
