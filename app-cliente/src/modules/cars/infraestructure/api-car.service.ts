import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CarRepository } from '../domain/car/CarRepository';
import { Observable } from 'rxjs';
import { Car } from '../domain/car/Car';

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

  // Criteria
  matching(filters:string ): Observable<Car[]>{
    // http://localhost:8090/cars/criteria?filters=jsonEncriptadoParaParametrosUrl&orderBy=eq&orderType=asc
    /*
      {
        filters : [
          {
            "field":"brand",
            "operator":"=",
            "value":"bmw"
          }
        ],
        orderBy: 'eq',
        orderType: 'asc'
      }
    */
   //const {order, filters}: Criteria = criteria
   // no es necesario codificar el filters, probablemente cambiar el endpoitn tambien
   const orderBy = 'eq';
   const orderType = 'asc';

   let params = new HttpParams()
   .set('filters', filters)
   .set('orderBy', orderBy)
  .set('orderType', orderType);
    return this.cliete.get<Car[]>(`http://localhost:8090/cars/criteria`, {params});
  }

  getAllCarBrands(): Observable<String[]> {
    return this.cliete.get<String[]>('http://localhost:8090/cars/brands');
  }

}
