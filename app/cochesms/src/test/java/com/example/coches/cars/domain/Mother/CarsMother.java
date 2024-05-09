package com.example.coches.cars.domain.Mother;

import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarId;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

final public class CarsMother {
	private CarStubId id;
	private CarStubTitle title;
	private CarStubDescription description;
	private CarStubBrand brand;
	private CarStubUrl carImageURL;
	private CarStubUrl carAnnouncementUrl;
	private CarStubPrice price;

	public CarsMother(CarStubId id, CarStubTitle title,
			CarStubDescription description, CarStubBrand brand,
			CarStubPrice price, CarStubUrl carImageURL,
			CarStubUrl carAnnouncementUrl) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.carImageURL = carImageURL;
		this.carAnnouncementUrl = carAnnouncementUrl;
	}
}
