package com.example.coches.cars.domain.car;
import java.util.Objects;

public class CarUrl {
	private String value;

	public CarUrl(String url) {
		this.value = this.asignarValor(url);
	}
	
	public String getValue() {
		return this.value;
	}
	
	private String asignarValor(String url) {
		// TODO Auto-generated method stub
		if(url == null || url.isBlank()) {
			return null;
		}
		return url;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarUrl other = (CarUrl) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return this.value;
	}
}
