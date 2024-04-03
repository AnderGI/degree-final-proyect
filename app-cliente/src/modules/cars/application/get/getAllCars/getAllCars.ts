import { Observable, firstValueFrom } from "rxjs";
import { CarRepository } from "../../../domain/CarRepository";
import { Car } from "../../../domain/Car";

export async function getAllCars(repository:CarRepository) : Promise<Car[]> {
    return firstValueFrom(repository.getAllCars())
}