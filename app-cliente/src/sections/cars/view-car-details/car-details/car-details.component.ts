import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Car } from '../../../../modules/cars/domain/car/Car';
import { getCar } from '../../../../modules/cars/application/get/getCar/getCarById';
import { CurrencyPipe, JsonPipe } from '@angular/common';
import { GetCarServiceHttpClientService } from '../../../../modules/cars/infraestructure/get-car-service/get-car-service-http-client.service';

@Component({
  selector: 'app-car-details',
  standalone: true,
  imports: [RouterLink , JsonPipe, CurrencyPipe],
  templateUrl: './car-details.component.html',
  styleUrl: './car-details.component.css'
})
export class CarDetailsComponent {
  public car!:Car;
  constructor(private apiRepo : GetCarServiceHttpClientService, private ruta:ActivatedRoute){
    ruta.params.subscribe((params) => {
      const {id} = params
      getCar(apiRepo.getCar)(id).then(data => {
        console.log(data);
        this.car = data
      })
    });
  }

  getAllCarDetails():String[]{
    return this.car.description.value.split(' | ').map((feature :String )=> feature.replace(feature[0], feature[0].toUpperCase()))
  }
}
