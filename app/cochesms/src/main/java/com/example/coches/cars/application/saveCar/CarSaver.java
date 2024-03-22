package com.example.coches.cars.application.saveCar;

import com.example.coches.cars.domain.car.Car;

final public class CarSaver {
	public static Car validateCar(Car carToValidate) {
		// De momento todos los campos son obligatorios
		// y si no hay uno devuelve null
		// Para el futuro implementar validadaores -> OCP
		// y que si no existe un campo x lanzen una excepccion

		if (carToValidate == null || 
			carToValidate.getIdValue() == null || 
			carToValidate.getTitleValue() == null || 
			carToValidate.getDescriptionValue() == null || 
			carToValidate.getCarPriceValue() == null || 
			carToValidate.getCarImageUrlValue() == null || 
			carToValidate.getCarAnnouncmentURLValue() == null) {
			return null;
		}

		return carToValidate;
	}
}
