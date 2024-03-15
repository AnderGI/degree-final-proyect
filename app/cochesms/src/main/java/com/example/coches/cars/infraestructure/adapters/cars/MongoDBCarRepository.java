package com.example.coches.cars.infraestructure.adapters.cars;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDTO;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.example.coches.cars.domain.criteria.Criteria;

@Repository 
@Primary
public class MongoDBCarRepository implements CarRepository {

	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public Car getCar(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> getCars() {
		// TODO Auto-generated method stub
		//System.out.println("Find all");
		List<CarDTO> carsDTO = mongoTemplate.findAll(CarDTO.class, "cars");
		List<Car> cars = new ArrayList<>();
        //System.out.println("After find all");
		for(CarDTO carDTO : carsDTO) {
			Car newCar = new Car(new CarTitle(carDTO.getTitle()), 
					new CarDescription(carDTO.getDescription()),
					new CarBrand(carDTO.getBrand()),
					new CarPrice(carDTO.getPrice()), 
					new CarUrl(carDTO.getCarImageURL()),
					new CarUrl(carDTO.getCarAnnouncementUrl()));
			cars.add(newCar);
		}
		return cars;
	}

	@Override
	public Car updateCar(Car car, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car deleteCar(String id) {
		// TODO Auto-generated method stub
		return null;
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
