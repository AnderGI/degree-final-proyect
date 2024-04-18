import { firstValueFrom } from "rxjs";
import { CarRepository } from "../../../domain/car/CarRepository";

export async function getAllCarBrands(repo:CarRepository): Promise<String[]>{
    return firstValueFrom(repo.getAllCarBrands());
}

/*
Aplicando currying pero primero testing
export function getAllCarBrands(repository:getAllCarBrands){
  return async function():Promise<CarBrand[]>{
    return await repository();
  }
}
*/
