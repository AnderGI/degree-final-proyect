package com.example.coches.cars.application.convertCarToJson;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.convertCarToObjectNode.CarAnnouncmentURLToObjectNodeConverter;
import com.example.coches.cars.domain.convertCarToObjectNode.CarBrandToObjectNodeConverter;
import com.example.coches.cars.domain.convertCarToObjectNode.CarDescriptionToObjectNodeConverter;
import com.example.coches.cars.domain.convertCarToObjectNode.CarFieldToObjectNodeConverter;
import com.example.coches.cars.domain.convertCarToObjectNode.CarIdToObjectNodeConverter;
import com.example.coches.cars.domain.convertCarToObjectNode.CarImageURLToObjectNodeConverter;
import com.example.coches.cars.domain.convertCarToObjectNode.CarPriceToObjectNodeConverter;
import com.example.coches.cars.domain.convertCarToObjectNode.CarTitleToObjectNodeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

final public class CarToJsonConverter {

    private static Map<String, CarFieldToObjectNodeConverter> convertersMap = new HashMap<>();
    static {
        convertersMap.put("id", new CarIdToObjectNodeConverter());
        convertersMap.put("title", new CarTitleToObjectNodeConverter());
        convertersMap.put("description", new CarDescriptionToObjectNodeConverter());
        convertersMap.put("brand", new CarBrandToObjectNodeConverter());
        convertersMap.put("price", new CarPriceToObjectNodeConverter());
        convertersMap.put("carImageURL", new CarImageURLToObjectNodeConverter());
        convertersMap.put("carAnnouncementURL", new CarAnnouncmentURLToObjectNodeConverter());
    }
	
	public static ObjectNode convert_car_to_json(Car car, ObjectMapper mapper) {
		
		ObjectNode jsonNode = mapper.createObjectNode();
		// OCP
		for(Entry<String, CarFieldToObjectNodeConverter> entry : convertersMap.entrySet()) {
			jsonNode.set(entry.getKey(), 
					entry.getValue().convertCarFieltToObjectNode(car, mapper));
		}
		
		
		return jsonNode;
	}
}
