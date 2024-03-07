package com.example.coches.cars.infraestructure.adapters.messagereceivers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.coches.cars.application.convertJsonToCar.JSONCarToModelCarConverter;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.messagereceiver.MessageReceiver;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
final public class RabbitMQMessageReceiver implements MessageReceiver {

	
	private final ObjectMapper objectMapper;

	public RabbitMQMessageReceiver(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void receiveMessage(byte[] message) {
		// TODO Auto-generated method stub
        try {
        	String messageString = new String(message, StandardCharsets.UTF_8);
        	List<Car> list = JSONCarToModelCarConverter.convertJSONCarToModelCarFromJSONString(messageString);
            System.out.println(list);	
            
        } catch (Exception e) {
            System.out.println("Error al procesar el mensaje: " + e.getMessage());
        }
    }
}
