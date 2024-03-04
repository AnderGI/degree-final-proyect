package com.example.coches.Domain.ValueObjects;

public class CarTitle {
	private String value;
	
	public CarTitle(String titulo) {
		this.value = this.asignarValor(titulo);
	}
	
	public String getValue() {
		return this.value;
	}
	
	private String asignarValor(String titulo) {
		// TODO Auto-generated method stub
		if(titulo != null || !titulo.isBlank()) {
			return titulo;
		}
		return null;
	}
}
