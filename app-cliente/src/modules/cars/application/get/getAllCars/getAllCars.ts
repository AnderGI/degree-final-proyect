import { firstValueFrom } from "rxjs";
import { CarRepository } from "../../../domain/car/CarRepository";
import { Car } from "../../../domain/car/Car"; 

export async function getAllCars(repository:CarRepository) : Promise<Car[]> {
    return firstValueFrom(repository.getAllCars())
}

/*
Currying pero primero testing 
export function getAllCars(repository:getAllCars){
  return async function():Promise<Car[]>{
    return await repository();
  }
}
*/
