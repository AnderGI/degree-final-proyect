package com.example.coches.Domain.Entities;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.coches.Application.ConvertJSONCarToCarModel.JSONCarToModelCarConverter;
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
        	JSONCarToModelCarConverter.convertJSONCarToModelCarFromJSONString(messageString);
            	
            
        } catch (Exception e) {
            System.out.println("Error al procesar el mensaje: " + e.getMessage());
        }
    }
}