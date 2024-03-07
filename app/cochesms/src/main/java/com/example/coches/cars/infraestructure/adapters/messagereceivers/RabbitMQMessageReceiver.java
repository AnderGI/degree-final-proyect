package com.example.coches.cars.infraestructure.adapters.messagereceivers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.coches.cars.application.convertJsonToCar.JSONCarToModelCarConverter;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.messagereceiver.MessageReceiver;
import com.fasterxml.jackson.databind.ObjectMapper;


final public class RabbitMQMessageReceiver implements MessageReceiver {

	
	private ObjectMapper objectMapper;
	
	private CarRepository repo;

	public RabbitMQMessageReceiver(ObjectMapper objectMapper, CarRepository repo) {
		this.objectMapper = objectMapper;
		this.repo = repo;
	}

	@Override
	public void receiveMessage(byte[] message) {
		// TODO Auto-generated method stub
        try {
        	String messageString = new String(message, StandardCharsets.UTF_8);
        	JSONCarToModelCarConverter converter = new JSONCarToModelCarConverter(repo);
        	converter.convertJSONCarToModelCarFromJSONString(messageString);
          	
            
        } catch (Exception e) {
            System.out.println("Error al procesar el mensaje: " + e.getMessage());
        }
    }
}
