import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GetAllCars } from '../../domain/car/CarRepository';
import { Car } from '../../domain/car/Car';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetAllCarServiceHttpClientService {

  
  constructor(private client:HttpClient) { }

  getAllCars:GetAllCars = async () => {
    const GET_ALL_CARS_ENDPOINT = "http://localhost:8090/cars";
    return firstValueFrom(this.client.get<Car[]>(`${GET_ALL_CARS_ENDPOINT}`));
  }
}
