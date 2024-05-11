import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Car, CarId } from '../../domain/car/Car';
import { firstValueFrom } from 'rxjs';
import { GetCar } from '../../domain/car/GetCarRepository';

@Injectable({
  providedIn: 'root'
})
export class GetCarServiceHttpClientService {

  constructor(private client:HttpClient) { }

  getCar: GetCar = async ({value}:CarId) => {
    const GET_CAR_API_ENDPOINT = "http://localhost:8090/cars/"
    return await firstValueFrom(this.client.get<Car>(`${GET_CAR_API_ENDPOINT}${value}`))
  }
}
