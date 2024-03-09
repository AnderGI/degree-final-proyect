package com.example.coches.cars.domain.car;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Objects;
/*
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
		CarPrice other = (CarPrice) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
}
*/

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

final public class CarPrice {
    private Double value;
    private static NumberFormat nfMoneda = NumberFormat.getInstance();
    @JsonCreator
    public CarPrice(String price) {
        this.value = asignarPrecio(price);
    }

    private Double asignarPrecio(String price) {
		// TODO Auto-generated method stub
    	if(price == null) return null;
    	Number n = null;
    	try {
    		n = nfMoneda.parse(price);
    	}catch(ParseException exc) {
    		return null;
    	}
    	
		return n.doubleValue();
	}

	public Double getCarPriceValue() {
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
        CarPrice other = (CarPrice) obj;
        return Objects.equals(value, other.value);
    }

    // Aquí podria aplicar algún tipo de formateador según locale -> Dominio
    @Override
    public String toString() {
        return getCarPriceValue().toString();
    }
}
