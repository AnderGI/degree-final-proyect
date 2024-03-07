package com.example.coches.Domain.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CarJSONModel {
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
