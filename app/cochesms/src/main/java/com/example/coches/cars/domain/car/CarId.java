package com.example.coches.cars.domain.car;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

final public class CarId {
	private String value;
	
	@JsonCreator
	public CarId(@JsonProperty("value") String value) {
		this.value = value;
	}
	
	public String getCarIdValue() {
		return value;
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
		CarId other = (CarId) obj;
		return Objects.equals(value, other.value);
	}
	
	
}
