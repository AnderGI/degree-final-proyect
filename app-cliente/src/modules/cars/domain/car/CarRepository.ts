import { Observable } from "rxjs";
import { Car } from "./Car";
import { Criteria, Filter } from "../criteria/Criteria";

export interface CarRepository {
    getCar : (id:String) => Observable<Car>,
    getAllCars: () => Observable<Car[]>
    matching: (filters:string) => Observable<Car[]>;
    getAllCarBrands: () => Observable<String[]>;
}