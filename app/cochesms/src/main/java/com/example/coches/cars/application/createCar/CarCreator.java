package com.example.coches.cars.application.createCar;

import com.example.coches.cars.application.convertJsonToCar.JSONCarToModelCarConverter;
import com.example.coches.cars.domain.car.Car;
import com.example.coches.cars.domain.car.CarBrand;
import com.example.coches.cars.domain.car.CarDTO;
import com.example.coches.cars.domain.car.CarDescription;
import com.example.coches.cars.domain.car.CarPrice;
import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.car.CarTitle;
import com.example.coches.cars.domain.car.CarUrl;
// RABBIT
final public class CarCreator {
	private CarRepository repo;

	public CarCreator(CarRepository repo) {
		this.repo = repo;
	}

	public void createEntityCarsFromRabbitMQJSONMessage(String message) {

		JSONCarToModelCarConverter jsonCarToEntityCarConverter = new JSONCarToModelCarConverter();
		CarDTO[] cars = null;
		try {
			cars = jsonCarToEntityCarConverter.convertJSONCarToModelCarFromJSONString(message);
		} catch (Exception e) {
			System.out.println("Error al procesar el mensaje: " + e.getMessage());
		}

		for (CarDTO carModel : cars) {
			Car car = Car.createCar(new CarTitle(carModel.getTitle()), new CarDescription(carModel.getDescription()),
					new CarBrand(carModel.getBrand()), new CarPrice(carModel.getPrice()),
					new CarUrl(carModel.getCarImageURL()), new CarUrl(carModel.getCarAnnouncementUrl()));
			repo.addCar(car);
		}

	}
}
