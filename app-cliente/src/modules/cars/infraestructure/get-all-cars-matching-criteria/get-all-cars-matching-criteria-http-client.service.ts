import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GetCarsMatchingCriteria } from '../../domain/car/CarRepository';
import { CriteriaJSON } from '../../domain/criteria/Criteria';
import { firstValueFrom } from 'rxjs';
import { Car } from '../../domain/car/Car';

@Injectable({
  providedIn: 'root'
})
export class GetAllCarsMatchingCriteriaHttpClientService {

  constructor(private client:HttpClient) { }

  getCarsMatchingCriteria:GetCarsMatchingCriteria = async (criteria:CriteriaJSON) => {
    const GET_CARS_MATCHING_CRITERIA:string = "http://localhost:8090/cars/criteria";
    const {filters, orderBy, orderType} = criteria;

    let params = new HttpParams()
    .set('filters', JSON.stringify(filters))
    .set('orderBy', orderBy)
   .set('orderType', orderType);
     return firstValueFrom(this.client.get<Car[]>(`${GET_CARS_MATCHING_CRITERIA}`, {params}));
  }
}
