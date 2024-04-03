import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CarRepository } from '../domain/CarRepository';
import { Observable } from 'rxjs';
import { Car } from '../domain/Car';

@Injectable({
  providedIn: 'root'
})
export class ApiCarService implements CarRepository{

  constructor(private cliete:HttpClient) { }

  getCar(id:String) : Observable<Car>{
    return this.cliete.get<Car>(`http://localhost:8090/cars/${id}`)
  }

  getAllCars() : Observable<Car[]> {
    return this.cliete.get<Car[]>("http://localhost:8090/cars");
  }
}
