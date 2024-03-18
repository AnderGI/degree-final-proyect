package com.example.coches.cars.domain.car;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(value = "car")
final public class Car implements Serializable{
	@Id
	private CarId id;
	private CarTitle title;
	private CarDescription description;
	private CarBrand brand;
	private CarUrl carImageURL;
	private CarUrl carAnnouncementUrl;
	private CarPrice price;
/*
    @JsonCreator
    public Car(@JsonProperty("title") CarTitle title,
               @JsonProperty("description") CarDescription description,
               @JsonProperty("brand") CarBrand brand,
               @JsonProperty("price") CarPrice price,
               @JsonProperty("carImageURL") CarUrl carImageURL,
               @JsonProperty("carAnnouncementURL") CarUrl carAnnouncementUrl) {
        this.id = new CarId();
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.carImageURL = carImageURL;
        this.carAnnouncementUrl = carAnnouncementUrl;
    }
  */
	   public Car() {
	    }
	 public Car(CarTitle title,
	            CarDescription description,
	            CarBrand brand,
	            CarPrice price,
	            CarUrl carImageURL,
	            CarUrl carAnnouncementUrl) {
	        this.id = new CarId();
	        this.title = title;
	        this.description = description;
	        this.brand = brand;
	        this.price = price;
	        this.carImageURL = carImageURL;
	        this.carAnnouncementUrl = carAnnouncementUrl;
	    }
    @JsonCreator
    public Car(@JsonProperty("id") CarId id,
    		   @JsonProperty("title") CarTitle title,
               @JsonProperty("description") CarDescription description,
               @JsonProperty("brand") CarBrand brand,
               @JsonProperty("price") CarPrice price,
               @JsonProperty("carImageURL") CarUrl carImageURL,
               @JsonProperty("carAnnouncementURL") CarUrl carAnnouncementUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.carImageURL = carImageURL;
        this.carAnnouncementUrl = carAnnouncementUrl;
    }
	// Name constructor https://codely.com/blog/constructores-semanticos
	public static Car createCar(CarTitle title, CarDescription description, CarBrand brand, CarPrice precio,
			CarUrl carImageUrl, CarUrl carAnnouncmentURL) {
		Car createdCar = new Car(title, description, brand, precio, carImageUrl, carAnnouncmentURL);

		return createdCar;
	}

	public String getIdValue() {
		return this.id.getCarIdValue();
	}

	public String getTitleValue() {
		return this.title.getValue();
	}

	public String getDescriptionValue() {
		return this.description.getValue();
	}

	public Double getCarPriceValue() {
		return this.price.getCarPriceValue();
	}

	public String getBrandValue() {
		return this.brand.getValue();
	}

	public String getCarImageUrlValue() {
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
/*
	public void updateCarPrice(Double price) {
		this.price = new CarPrice(price);
	}
	*/

	@Override
	public String toString() {
		return "Car [id=" + id + ", title=" + title + ", description=" + description + ", brand=" + brand
				+ ", carImageURL=" + carImageURL + ", carAnnouncementUrl=" + carAnnouncementUrl + ", price=" + price
				+ "]";
	}

}
