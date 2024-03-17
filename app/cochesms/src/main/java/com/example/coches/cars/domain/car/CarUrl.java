package com.example.coches.cars.domain.car;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

final public class CarUrl {
	private String value;

	@JsonCreator
	public CarUrl(@JsonProperty("value") String value) {
		this.value = this.asignarValor(value);
	}

	public String getValue() {
		return this.value;
	}

	private String asignarValor(String url) {
		// TODO Auto-generated method stub
		if (url == null || url.isBlank()) {
			return null;
		}
		// Validación de https://www.baeldung.com/java-validate-url#validate-url-using-jdk
		try {
			new URL(url).toURI();
			return url;
		} catch (URISyntaxException exception) {
			return null;
		} catch (MalformedURLException exception) {
			return null;
		}
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
