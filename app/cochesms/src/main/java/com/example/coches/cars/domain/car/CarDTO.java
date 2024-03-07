package com.example.coches.cars.domain.car;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CarDTO {
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
