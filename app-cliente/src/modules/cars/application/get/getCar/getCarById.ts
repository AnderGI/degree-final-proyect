import { Observable, firstValueFrom } from "rxjs";
import { Car } from "../../../domain/Car";
import { CarRepository } from "../../../domain/CarRepository";

export async function getCarById(repository: CarRepository, id: String) : Promise<Car> {
    return firstValueFrom(repository.getCar(id));
}