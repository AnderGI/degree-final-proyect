package com.example.coches.cars.domain.car;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

final public class CarDescription {
	private String value;
	@JsonCreator
	public CarDescription(@JsonProperty("value") String value) {
		this.value = this.asignarValor(value);
	}
	

	public String getValue() {
		return this.value;
	}

	private String asignarValor(String descripccion) {
		// TODO Auto-generated method stub
		if (descripccion == null || descripccion.isBlank())
			return null;
		return descripccion.toLowerCase();

	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
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
}
