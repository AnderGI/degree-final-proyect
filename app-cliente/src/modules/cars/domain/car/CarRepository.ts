import { Observable } from "rxjs";
import { Car } from "./Car";
import { CriteriaJSON } from "../criteria/Criteria";

export interface CarRepository {
    getCar : (id:String) => Observable<Car>,
    getAllCars: () => Observable<Car[]>
    matching: (criteriaJson:CriteriaJSON) => Observable<Car[]>; // en vez de filters tendriamos el criteria
    getAllCarBrands: () => Observable<String[]>;
}

// Cambiar a funcional
// const type getCar = (id:String) => Promise<Car>;
// const type getAllCars = () => Promise<Car[]>;
// const type matching = (criteria:Criteria) => Promise<Car[]>
// const type getAllCarBrands = () => Promise<CarBrand[]> esto habría que cambiarlo a algo más semantico type CarBrand
