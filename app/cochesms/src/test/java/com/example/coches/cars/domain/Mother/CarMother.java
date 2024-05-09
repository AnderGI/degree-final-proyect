package com.example.coches.cars.domain.Mother;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarId;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

final public class CarMother {
	private CarIdMother id;
	private CarTitleMother title;
	private CarDescriptionMother description;
	private CarBrandMother brand;
	private CarImageUrlMother carImageURL;
	private CarUrlMother carAnnouncementUrl;
	private CarPriceMother price;

	public static Car create() {
		return new Car(
				CarIdMother.random(), CarTitleMother.random(), 
				CarDescriptionMother.random(), CarBrandMother.random(),
				CarPriceMother.random() ,CarImageUrlMother.random(),
				CarUrlMother.random());
	}
}
