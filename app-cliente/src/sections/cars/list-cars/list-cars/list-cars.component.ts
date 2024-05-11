import { Component } from '@angular/core';
import { getAll } from '../../../../modules/cars/application/get/getAllCars/getAllCars';
import { Car } from '../../../../modules/cars/domain/car/Car';
import { CarComponent } from '../../car/car/car.component';
import { ActivatedRoute } from '@angular/router';
import { GetAllCarServiceHttpClientService } from '../../../../modules/cars/infraestructure/get-all-car-service/get-all-car-service-http-client.service';

@Component({
  selector: 'app-list-cars',
  standalone: true,
  imports: [CarComponent],
  templateUrl: './list-cars.component.html',
  styleUrl: './list-cars.component.css'
})
export class ListCarsComponent {
  public cars!:Car[];
  constructor(private apiRepo:GetAllCarServiceHttpClientService, private route: ActivatedRoute){
    this.route.queryParams.subscribe(params => {
      const {cars} = params
      if (cars) {
        this.cars = JSON.parse(cars);
      } else {
        getAll(apiRepo.getAllCars)().then(data => {
          this.cars = data
        })
      }
    });
  }
}
