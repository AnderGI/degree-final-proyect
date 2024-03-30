package com.example.coches.cars.domain.convert_car_model_to_json_model;

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
	// Genero un mapa dependiente de las propiedades del car que se quieran enviar por json
    // Key -> Nombre de la propiedad del car model 
	// ** (hay que tener en cuenta que son value objects)
	// Value -> Convertidor específico para cada propiedad
	// ** como el proceso soolo es dependiente de una propiedad genero una interfaz
	// y aplico OCP para facilitar el desacoplamiento
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
    
	public static ObjectNode convert(Car car, ObjectMapper mapper) {
		// Nodo raíz
		ObjectNode jsonNode = mapper.createObjectNode();
		// Cada convertidor crea un nodo a partir del objectmapper ya instanciado
		// y coge el valor que necesite para generar el nodo que representa el value object
		// {"campo": {"value": valor }}
		for(Entry<String, CarFieldToObjectNodeConverter> entry : convertersMap.entrySet()) {
			jsonNode.set(entry.getKey(), 
					entry.getValue().convertCarFieltToObjectNode(car, mapper));
		}
		
		
		return jsonNode;
	}
}
