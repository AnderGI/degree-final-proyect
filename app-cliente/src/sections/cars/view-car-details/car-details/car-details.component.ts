import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Car } from '../../../../modules/cars/domain/Car';
import { ApiCarService } from '../../../../modules/cars/infraestructure/api-car.service';
import { getCarById } from '../../../../modules/cars/application/get/getCar/getCarById';
import { CurrencyPipe, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-car-details',
  standalone: true,
  imports: [RouterLink , JsonPipe, CurrencyPipe],
  templateUrl: './car-details.component.html',
  styleUrl: './car-details.component.css'
})
export class CarDetailsComponent {
  public car!:Car;
  constructor(private apiRepo : ApiCarService, private ruta:ActivatedRoute){
    ruta.params.subscribe((params) => {
      const {id} = params
      getCarById(apiRepo, id).then(data => {
        console.log(data);
        this.car = data
      })
    });
  }

  getAllCarDetails():String[]{
    return this.car.description.value.split(' | ').map(feature => feature.replace(feature[0], feature[0].toUpperCase()))
  }
}
