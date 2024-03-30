package com.example.coches.cars.domain.criteria;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

final public class Filters {

	private List<Filter> filters;
	public Filters(List<Filter> filters) {
		this.filters = filters;
	}
	public void add(Filter filter)
	{
		filters.add(filter);
	}
	
	public List<Filter> getFiltersList(){
		return this.filters;
	}
	
	public static Filters fromPrimitives(String urlEncodedListOfFilters) {
		ObjectMapper mapper = new ObjectMapper();
		List<Filter> filters = new ArrayList<>();
		String utf8DecodedListOfFilters = null;
		// transformar estos filtros
		// Gestionar mejor los errores
		System.out.println("Filters from primitives");
		try {
			utf8DecodedListOfFilters = URLDecoder.decode(urlEncodedListOfFilters, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Decoded " + utf8DecodedListOfFilters);
		
		ArrayNode fieldsArrayJson = null;
		 try {
			 fieldsArrayJson = (ArrayNode) mapper.readTree(utf8DecodedListOfFilters);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 for(JsonNode fieldNode : fieldsArrayJson) {
			 String field = fieldNode.get("field").asText();
			 String operator = fieldNode.get("operator").asText();
			 String value = fieldNode.get("value").asText();
			 filters.add(
					 new Filter(
							 new FilterField(field), 
							 new FilterOperator(operator), 
							 new FilterValue(value))
			);
		 }
		 
		// de esa lista ir creando filtros y que sean añadidos a la 
		// clase Filters a retornar
		return new Filters(filters);
	}
	@Override
	public String toString() {
		return "Filters [filters=" + filters + "]";
	}
	
	
}
