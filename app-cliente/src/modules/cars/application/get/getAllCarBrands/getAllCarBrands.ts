import { firstValueFrom } from "rxjs";
import { CarRepository } from "../../../domain/car/CarRepository";

export async function getAllCarBrands(repo:CarRepository): Promise<String[]>{
    return firstValueFrom(repo.getAllCarBrands());
}