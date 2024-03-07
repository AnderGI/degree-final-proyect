package com.example.coches.cars.domain.car;

import java.util.Objects;

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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarDescription other = (CarDescription) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return this.value;
	}
}
