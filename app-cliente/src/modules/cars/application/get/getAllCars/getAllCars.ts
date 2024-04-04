import { Observable, firstValueFrom } from "rxjs";
import { CarRepository } from "../../../domain/car/CarRepository";
import { Car } from "../../../domain/car/Car"; 

export async function getAllCars(repository:CarRepository) : Promise<Car[]> {
    return firstValueFrom(repository.getAllCars())
}