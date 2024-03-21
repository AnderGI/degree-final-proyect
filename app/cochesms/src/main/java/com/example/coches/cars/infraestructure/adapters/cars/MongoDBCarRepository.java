package com.example.coches.cars.infraestructure.adapters.cars;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarId;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.example.coches.cars.domain.criteria.Criteria;
import com.mongodb.client.result.DeleteResult;

@Repository 
@Primary
public class MongoDBCarRepository implements CarRepository {

	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	@Override
	public Car getCar(String id) {
		// TODO Auto-generated method stub
		
		Query query = new Query();
		query.addCriteria(org.springframework.data.mongodb.core.query.Criteria.where("_id")
				.is(new CarId(id)));
		Car car= mongoTemplate.findOne(query, Car.class, "cars");
		return car;

	}

	@Override
	public List<Car> getCars() {
		// TODO Auto-generated method stub
		// System.out.println(mongoTemplate.findAll(Car.class, "cars"));
		//List<CarDTO> carsDTO = mongoTemplate.findAll(CarDTO.class, "cars");
		//return DatabaseCarToJSONCarConverter.convert_database_cars_to_json_response_cars(carsDTO);
		return mongoTemplate.findAll(Car.class, "cars");
	}

	@Override
	public Car updateCar(Car car, String id) {
	    // Verificar si el carro que se quiere actualizar existe
	    Car existingCar = getCar(id);
	    if (existingCar == null) {
	        return null; // El carro no existe, devuelve null
	    }

	    // Crear el filtro de consulta para encontrar el carro por su ID
	    Query query = new Query(org.springframework.data.mongodb.core.query.Criteria.where("_id").is(new CarId(id)));

	    // Crear la definición de actualización con los valores del nuevo carro
	    Update update = new Update();
	    update.set("title", new CarTitle(car.getTitleValue()));
	    update.set("description", new CarDescription(car.getDescriptionValue()));
	    update.set("brand", new CarBrand(car.getBrandValue()));
	    update.set("price", new CarPrice(car.getCarPriceValue()));
	    update.set("carImageURL", new CarUrl(car.getCarImageUrlValue()));
	    update.set("carAnnouncementUrl", new CarUrl(car.getCarAnnouncmentURLValue()));

	    // Ejecutar la actualización en la base de datos
	    mongoTemplate.updateFirst(query, update, Car.class, "cars");

	    // Devolver el carro actualizado
	    return getCar(id);
	}


	@Override
	public Car deleteCar(String id) {
		// TODO Auto-generated method stub
		Car toDeleteCar = getCar(id);
		Query deletionQuery = new Query();
		deletionQuery.query(org.springframework.data.mongodb.core.query.Criteria.where("_id")
				.is(new CarId(id)));
		DeleteResult deletedResult = mongoTemplate.remove(deletionQuery, Car.class, "cars");
		return toDeleteCar;
	}

	@Override
	public Car addCar(Car car) {
		// TODO Auto-generated method stub
		// Validar que todos los campos del car sean correctos
		Car addedCar = mongoTemplate.insert(car, "cars");
		return addedCar;
	}

	@Override
	public List<Car> findByCriteria(Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
