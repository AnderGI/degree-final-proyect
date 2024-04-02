import { Observable } from "rxjs";
import { Car } from "./Car";

export interface CarRepository {
    getCar : (id:String) => Observable<Car>,
}