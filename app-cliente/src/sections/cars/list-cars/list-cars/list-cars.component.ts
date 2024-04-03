import { Component } from '@angular/core';
import { CarRepository } from '../../../../modules/cars/domain/CarRepository';
import { ApiCarService } from '../../../../modules/cars/infraestructure/api-car.service';
import { getAllCars } from '../../../../modules/cars/application/get/getAllCars/getAllCars';
import { Car } from '../../../../modules/cars/domain/Car';
import { CarComponent } from '../../car/car/car.component';

@Component({
  selector: 'app-list-cars',
  standalone: true,
  imports: [CarComponent],
  templateUrl: './list-cars.component.html',
  styleUrl: './list-cars.component.css'
})
export class ListCarsComponent {
  public cars!:Car[];
  constructor(private apiRepo:ApiCarService){
    getAllCars(apiRepo).then(data => {
      console.log(data)
      this.cars = data
    })
  }
}
