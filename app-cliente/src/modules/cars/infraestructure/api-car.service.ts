import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CarRepository } from '../domain/car/CarRepository';
import { Observable } from 'rxjs';
import { Car } from '../domain/car/Car';
import { CriteriaJSON } from '../domain/criteria/Criteria';

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
  matching(criteriaJson:CriteriaJSON): Observable<Car[]>{
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
   const {filters, orderBy, orderType} = criteriaJson;

   let params = new HttpParams()
   .set('filters', JSON.stringify(filters))
   .set('orderBy', orderBy)
  .set('orderType', orderType);
    return this.cliete.get<Car[]>(`http://localhost:8090/cars/criteria`, {params});
  }

  getAllCarBrands(): Observable<String[]> {
    return this.cliete.get<String[]>('http://localhost:8090/cars/brands');
  }

}

/*
Lo que habría que hacer modificar las funciones del servie (y hacer más semático su nombre) y que estos devuelvan el tipo
Probablemente hacer un service por cada type
Una solucion algo más liosa por ser angular un framework más ligado a clases y POO que a funcionañ

Para get car
@Injectable({
  providedIn: 'root'
})
export class GetCarByIdHttpClientService {

  constructor(private cliete:HttpClient) { }
  
  getCar() : getCar {
  // Tipo a devolver
  // const type getCar = ({id:CarId}) => Promise<Car>;
    const GET_CAR_BY_ID_ENDPOINT: string = "http://localhost:8090/cars/";
      return async function ({id:CarId}) {
        await firstValueFrom(this.cliete.get<Car>(GET_CAR_BY_ID_ENDPOINT + id.value))
      }
    }

}




*/
