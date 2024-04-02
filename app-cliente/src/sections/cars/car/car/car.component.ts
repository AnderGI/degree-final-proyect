import { Component } from '@angular/core';
import { ApiCarService } from '../../../../modules/cars/infraestructure/api-car.service';
import { getCarById } from '../../../../modules/cars/application/get/getCar/getCarById';

@Component({
  selector: 'app-car',
  standalone: true,
  imports: [],
  templateUrl: './car.component.html',
  styleUrl: './car.component.css'
})
export class CarComponent {
  constructor(private apiRepo : ApiCarService) {
    getCarById(apiRepo, "aa7052b6-31b2-428d-bca9-21b5111e195e")
    .then(data => console.log(data))
  }
}
