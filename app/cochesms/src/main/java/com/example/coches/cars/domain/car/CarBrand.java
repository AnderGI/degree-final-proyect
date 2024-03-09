package com.example.coches.cars.domain.car;

import java.util.Objects;

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
		if(brand == null || brand.isBlank()) return null;
		return brand.toLowerCase();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarBrand other = (CarBrand) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return this.value;
	}
}
