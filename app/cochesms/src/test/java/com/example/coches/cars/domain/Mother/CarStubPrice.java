package com.example.coches.cars.domain.Mother;

import java.text.NumberFormat;

import com.github.javafaker.Faker;

final public class CarStubPrice {
	private Double value;
	private static NumberFormat nfMoneda = NumberFormat.getInstance();
	
	public CarStubPrice(Double value) {
		this.value = value;
	}
	
	public static CarStubPrice random() {
		return new CarStubPrice(new Faker().number().randomDouble(3,20000, 200000));
	}
	


	@Override
	public String toString() {
		return nfMoneda.format(value);
	}
}
