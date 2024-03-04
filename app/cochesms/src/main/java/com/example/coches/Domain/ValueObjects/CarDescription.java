package com.example.coches.Domain.ValueObjects;

public class CarDescription {
	private String value;
	
	public CarDescription(String titulo) {
		this.value = this.asignarValor(titulo);
	}
	
	public String getValue() {
		return this.value;
	}
	
	private String asignarValor(String descripccion) {
		// TODO Auto-generated method stub
		if(descripccion != null || !descripccion.isBlank()) {
			return descripccion;
		}
		return null;
	}
}
