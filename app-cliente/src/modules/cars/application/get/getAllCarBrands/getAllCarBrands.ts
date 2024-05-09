import { GetAllCarBrands } from "../../../domain/car/GetCarBrandsRepository";
import { CarBrand } from "../../../domain/car/Car";

/*
export async function getAllCarBrands(repo:CarRepository): Promise<String[]>{
    return firstValueFrom(repo.getAllCarBrands());
}
*/

export function getAllCarBrands(repository:GetAllCarBrands){
  return async function():Promise<CarBrand[]>{
    return await repository();
  }
}

