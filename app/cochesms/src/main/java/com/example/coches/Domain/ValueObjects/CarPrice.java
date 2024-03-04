package com.example.coches.Domain.ValueObjects;

import java.text.NumberFormat;
import java.text.ParseException;

public class CarPrice {
	private Double value;
	private static NumberFormat decimal = NumberFormat.getInstance();
	
	public CarPrice(Double precio) {
		decimal.setMaximumFractionDigits(2);
		decimal.setMinimumFractionDigits(2);
		this.value = this.asignarValor(precio);
	}
	
	public Double getValue() {
		return this.value;
	}
	
	private Double asignarValor(Double precioStr) {
		// TODO Auto-generated method stub
		if(precioStr != null || precioStr >= 0) {
			return precioStr;
		}
		return null;
	}
}
