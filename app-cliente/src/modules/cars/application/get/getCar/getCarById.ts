import { firstValueFrom } from "rxjs";
import { Car, CarId } from "../../../domain/car/Car";
import { CarRepository, GetCar } from "../../../domain/car/CarRepository";

export async function getCarById(repository: CarRepository, id: String) : Promise<Car> {
    return firstValueFrom(repository.getCar(id));
}

// Currying
export function getCar(repository:GetCar){
    return async function ({value}:CarId) {
        return await repository({value});
    }
}





