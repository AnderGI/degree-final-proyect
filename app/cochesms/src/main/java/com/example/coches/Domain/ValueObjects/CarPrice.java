package com.example.coches.Domain.ValueObjects;

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

public class CarPrice {
    private Double value;

    @JsonCreator
    public CarPrice(@JsonProperty("price") String price) {
        this.value = parsePrice(price);
    }

    public String getValue() {
        return formatPrice(value);
    }

    private Double parsePrice(String priceStr) {
        if (priceStr == null) {
            return null;
        }
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            symbols.setGroupingSeparator(',');
            symbols.setDecimalSeparator('.');
            DecimalFormat format = new DecimalFormat("#,##0.00", symbols);
            format.setParseBigDecimal(true);
            return format.parse(priceStr).doubleValue();
        } catch (Exception e) {
            // En caso de error, devuelve null
            return null;
        }
    }

    private String formatPrice(Double price) {
        if (price != null) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            symbols.setGroupingSeparator(',');
            symbols.setDecimalSeparator('.');
            DecimalFormat format = new DecimalFormat("#,##0.00", symbols);
            return format.format(price);
        } else {
            return "";
        }
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
        return getValue();
    }
}
