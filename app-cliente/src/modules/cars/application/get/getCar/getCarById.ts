import { firstValueFrom } from "rxjs";
import { Car } from "../../../domain/car/Car";
import { CarRepository } from "../../../domain/car/CarRepository";

export async function getCarById(repository: CarRepository, id: String) : Promise<Car> {
    return firstValueFrom(repository.getCar(id));
}

/*
Aplicando currying...
Lo que va a recibir es el CarId
Antes de implementarlo ---> TESTING!!!!!
export function getCarById(repository:getCar) {
  return async function({id:CarId}):Promise<Car>{
     return firstValueFrom(repository(id));
  }
}



Implementar tests
https://medium.com/@philip.mutua/setting-up-jest-in-your-angular-16-project-3638ef65f3a3


*/
