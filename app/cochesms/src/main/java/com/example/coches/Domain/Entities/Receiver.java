package com.example.coches.Domain.Entities;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Receiver {

	@Autowired
	private final ObjectMapper objectMapper;

	public Receiver(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void receiveMessage(byte[] message) {
        try {
        	String messageString = new String(message, StandardCharsets.UTF_8);
        	 ObjectMapper objectMapper = new ObjectMapper();
             
        	 CarModel[] cars = objectMapper.readValue(messageString, CarModel[].class);
        	 List<Car> carsL = new ArrayList();
            for (CarModel carModel : cars) {
              //  System.out.println("Título del coche: " + car.getTitleValue());
            	carsL.add(new Car(
            carModel.getTitle(),
            carModel.getDescription(),
            carModel.getBrand(),
            carModel.getPrice(),
            carModel.getCarImageURL(),
            carModel.getCarAnnouncementUrl()
        ));
            	
            }
        } catch (Exception e) {
            System.out.println("Error al procesar el mensaje: " + e.getMessage());
        }
    }
}