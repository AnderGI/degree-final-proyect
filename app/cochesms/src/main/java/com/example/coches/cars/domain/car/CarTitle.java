package com.example.coches.cars.domain.car;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

final public class CarTitle {
	private String value;
	
	@JsonCreator
	public CarTitle(@JsonProperty("value") String value) {
		this.value = this.asignarValor(value);
	}
	
	public String getValue() {
		return this.value;
	}
	
	private String asignarValor(String titulo) {
		// TODO Auto-generated method stub
		if(titulo == null || titulo.isBlank())return null;
		return titulo.toLowerCase();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarTitle other = (CarTitle) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return this.value;
	}
}
