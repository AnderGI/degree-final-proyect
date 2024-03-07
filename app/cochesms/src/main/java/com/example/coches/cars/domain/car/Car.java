package com.example.coches.cars.domain.car;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

final public class Car {
	private String id;
	private CarTitle title;
	private CarDescription description;
	private CarBrand brand;
	private CarUrl carImageURL;
	private CarUrl carAnnouncementUrl;
	private CarPrice price;

	public Car(String title, String description, String brand, String precio, String carImageUrl,
			String carAnnouncmentURL) {
		this.id = UUID.randomUUID().toString();
		this.title = new CarTitle(title);
		this.description = new CarDescription(description);
		this.brand = new CarBrand(brand);
		this.price = new CarPrice(precio);
		this.carImageURL = new CarUrl(carImageUrl);
		this.carAnnouncementUrl = new CarUrl(carAnnouncmentURL);
	}

	public String getIdValue() {
		return this.id;
	}

	public String getTitleValue() {
		return this.title.getValue();
	}

	public String getDescriptionValue() {
		return this.description.getValue();
	}

	public String getDoubleValue() {
		return this.price.getValue();
	}

	public String getBrandValue() {
		return this.brand.getValue();
	}

	public String getCariMAGEURLValue() {
		return this.carImageURL.getValue();
	}

	public String getCarAnnouncmentURLValue() {
		return this.carAnnouncementUrl.getValue();
	}

	public void updateTitle(String title) {
		this.title = new CarTitle(title);
	}

	public void updateDescription(String description) {
		this.description = new CarDescription(description);
	}

	public void updateCarImageUrl(String url) {
		this.carImageURL = new CarUrl(url);
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", title=" + title + ", description=" + description + ", brand=" + brand
				+ ", carImageURL=" + carImageURL + ", carAnnouncementUrl=" + carAnnouncementUrl + ", price=" + price
				+ "]";
	}

	
	
	
	
}
