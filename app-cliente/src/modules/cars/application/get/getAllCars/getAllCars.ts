import { firstValueFrom } from "rxjs";
import { CarRepository, } from "../../../domain/car/CarRepository";
import { Car } from "../../../domain/car/Car"; 
import { GetAllCars } from "../../../domain/car/GetAllCarsRepository";

export async function getAllCars(repository:CarRepository) : Promise<Car[]> {
    return firstValueFrom(repository.getAllCars())
}

export function getAll(repository:GetAllCars){
    return async function(){
        return await repository();
    }
}
