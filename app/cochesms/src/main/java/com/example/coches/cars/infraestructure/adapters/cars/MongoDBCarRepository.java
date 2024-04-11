package com.example.coches.cars.infraestructure.adapters.cars;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.coches.cars.application.save_car.CarSaver;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarId;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
import com.example.coches.cars.domain.criteria.Criteria;
import com.example.coches.cars.domain.criteria.Filter;
import com.example.coches.cars.domain.criteria.FilterOperator;
import com.example.coches.cars.domain.criteria.Order;
import com.example.coches.cars.domain.criteria.OrderBy;
import com.example.coches.cars.domain.criteria.OrderType;
import com.example.coches.cars.domain.criteria_filters.MongoQueryCriteriaCreatorFactory;
import com.example.coches.cars.domain.criteria_order.FromCriteriaOrderSortCreatorFactory;
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
		return mongoTemplate.findAll(Car.class, "cars");
	}

	@Override
	public Car updateCar(Car car, String id) {
	   Car existingCar = getCar(id);
	    if (existingCar == null) {
	        return null; 
	    }
	    // Verificar el car en este caso tendria que verificar todos los campos que != null
	    // A nivel de UI habria un formulario que impedirira enviar campos con valores vacios
	    
	    Car toValidateCar = CarSaver.validateCar(car);
	    
	    if(toValidateCar == null) return null;
	   
	    Query query = new Query(org.springframework.data.mongodb.core.query.Criteria.where("_id").is(new CarId(id)));
	    Update update = new Update();
	    update.set("title", new CarTitle(car.getTitleValue()));
	    update.set("description", new CarDescription(car.getDescriptionValue()));
	    update.set("brand", new CarBrand(car.getBrandValue()));
	    update.set("price", new CarPrice(car.getCarPriceValue()));
	    update.set("carImageURL", new CarUrl(car.getCarImageUrlValue()));
	    update.set("carAnnouncementUrl", new CarUrl(car.getCarAnnouncmentURLValue()));
	    mongoTemplate.updateFirst(query, update, Car.class, "cars");


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
	public List<Car> matching(Criteria criteria) {
		// TODO Auto-generated method stub
		Query query = new Query();
		// Esto es para filtros
		for(Filter filter : criteria.getFilters()) {
		
			MongoQueryCriteriaCreatorFactory factory = new MongoQueryCriteriaCreatorFactory();
			query.addCriteria(
					factory.getMongoCriteriaCreator(filter).createQuery(filter)
			);
		}
		
		// Esto para la ordenación
		// OrderType
		// De momento por casos de uso, solo habra un ordering y sera por defector por el campo precio
		Order order = criteria.getOrder();
		FromCriteriaOrderSortCreatorFactory sortCreatorFactory = new FromCriteriaOrderSortCreatorFactory(order);
		query.with(sortCreatorFactory.create_sort_from_criteria_order());
		
		
		return mongoTemplate.find(query, Car.class, "cars");
	}

	@Override
	public List<String> getAllBrands() {
		// TODO Auto-generated method stub
		return getCars().stream()
				.map(car -> car.getBrandValue())
				.distinct()
				.toList();
	}

}
