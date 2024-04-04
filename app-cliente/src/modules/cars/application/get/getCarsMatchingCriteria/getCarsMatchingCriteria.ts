import { firstValueFrom } from "rxjs";
import { Car } from "../../../domain/car/Car";
import { CarRepository } from "../../../domain/car/CarRepository";
import { Criteria, Filter } from "../../../domain/criteria/Criteria";

export async function getCarsMatchingCriteria(apiRepo: CarRepository, filters:string): Promise<Car[]>{
    return firstValueFrom(apiRepo.matching(filters));
} 