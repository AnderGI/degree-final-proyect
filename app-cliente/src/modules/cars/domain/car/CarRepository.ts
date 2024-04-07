import { Observable } from "rxjs";
import { Car } from "./Car";
import { CriteriaJSON } from "../criteria/Criteria";

export interface CarRepository {
    getCar : (id:String) => Observable<Car>,
    getAllCars: () => Observable<Car[]>
    matching: (criteriaJson:CriteriaJSON) => Observable<Car[]>; // en vez de filters tendriamos el criteria
    getAllCarBrands: () => Observable<String[]>;
}