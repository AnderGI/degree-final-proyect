package com.example.coches.cars.infraestructure.adapters.cars;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarId;
import com.example.coches.cars.domain.car.CarRepository;
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
		// TODO Auto-generated method stub
		return null;
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
		Car addedCar = mongoTemplate.insert(car, "cars");
		return addedCar;
	}

	@Override
	public List<Car> findByCriteria(Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
