package com.example.coches.Domain.Entities;

import com.example.coches.Domain.ValueObjects.CarBrand;
import com.example.coches.Domain.ValueObjects.CarDescription;
import com.example.coches.Domain.ValueObjects.CarPrice;
import com.example.coches.Domain.ValueObjects.CarTitle;

public class Car {
	private CarTitle title;
	private CarDescription description;
	private CarBrand brand;
	private String carImageURL;
	private String carAnnouncement;
	private CarPrice price;
	
	public Car(
			String title, 
			String description,
			String brand,
			Double precio) {
		this.title = new CarTitle(title);
		this.description = new CarDescription(description);
		this.brand = new CarBrand(brand);
		this.price = new CarPrice(precio);
	}
	
	public String getTitleValue() {
		return this.title.getValue();
	}
	
	public String getDescriptionValue() {
		return this.description.getValue();
	}
	
	public Double getDoubleValue() {
		return this.price.getValue();
	}

	public String getBrandValue() {
		return this.brand.getValue();
	}	
	
	public void updateTitle(String title) {
		this.title = new CarTitle(title);
	}
	
	public void updateDescription(String description) {
		this.description = new CarDescription(description);
	}
}
