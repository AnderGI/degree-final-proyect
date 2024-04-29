import { firstValueFrom } from "rxjs";
import { CarRepository, GetAllCars } from "../../../domain/car/CarRepository";
import { Car } from "../../../domain/car/Car"; 

export async function getAllCars(repository:CarRepository) : Promise<Car[]> {
    return firstValueFrom(repository.getAllCars())
}

export function getAll(repository:GetAllCars){
    return async function(){
        return await repository();
    }
}
