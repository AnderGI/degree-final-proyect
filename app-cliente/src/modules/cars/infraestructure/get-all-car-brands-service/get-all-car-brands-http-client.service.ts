import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { CarBrand } from '../../domain/car/Car';
import { GetAllCarBrands } from '../../domain/car/GetCarBrandsRepository';

@Injectable({
  providedIn: 'root'
})
export class GetAllCarBrandsHttpClientService {

  constructor(private client:HttpClient) { }

  getAllCarBrands:GetAllCarBrands = async () => {
    const GET_ALL_CAR_BRANDS_ENDPOINT = "http://localhost:8090/cars/brands"
    return firstValueFrom(this.client.get<CarBrand[]>(`${GET_ALL_CAR_BRANDS_ENDPOINT}`))
  }

}
