package com.example.coches.Domain.ValueObjects;

public class CarBrand {
	private String value;
	
	public CarBrand(String brand) {
		this.value = this.asignarValor(brand);
	}
	
	public String getValue() {
		return this.value;
	}
	
	private String asignarValor(String brand) {
		// TODO Auto-generated method stub
		if(brand != null || !brand.isBlank()) {
			return brand;
		}
		return null;
	}
}
