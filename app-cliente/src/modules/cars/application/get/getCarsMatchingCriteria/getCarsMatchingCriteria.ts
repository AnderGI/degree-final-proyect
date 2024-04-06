import { firstValueFrom } from "rxjs";
import { Car } from "../../../domain/car/Car";
import { CarRepository } from "../../../domain/car/CarRepository";




// Aqui es donde se le van a pasar el Criteria ya construido

export async function getCarsMatchingCriteria(apiRepo: CarRepository, filters:string): Promise<Car[]>{
    return firstValueFrom(apiRepo.matching(filters));
} 