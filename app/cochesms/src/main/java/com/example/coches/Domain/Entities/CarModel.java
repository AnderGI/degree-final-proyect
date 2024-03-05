package com.example.coches.Domain.Entities;

import com.example.coches.Domain.ValueObjects.CarBrand;
import com.example.coches.Domain.ValueObjects.CarDescription;
import com.example.coches.Domain.ValueObjects.CarPrice;
import com.example.coches.Domain.ValueObjects.CarTitle;
import com.example.coches.Domain.ValueObjects.CarUrl;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CarModel {
	@JsonProperty("id")
	private String id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("description")
	private String description;
	@JsonProperty("brand")
	private String brand;
	@JsonProperty("carImageURL")
	private String carImageURL;
	@JsonProperty("carAnnouncement")
	private String carAnnouncementUrl;
	@JsonProperty("price")
	private String price;
}
