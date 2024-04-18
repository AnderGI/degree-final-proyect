import { firstValueFrom } from "rxjs";
import { Car } from "../../../domain/car/Car";
import { CarRepository } from "../../../domain/car/CarRepository";
import { CriteriaJSON } from "../../../domain/criteria/Criteria";




// Aqui es donde se le van a pasar el Criteria ya construido

export async function getCarsMatchingCriteria(apiRepo: CarRepository, criteriaJSON:CriteriaJSON): Promise<Car[]>{
    return firstValueFrom(apiRepo.matching(criteriaJSON));
} 


/*
Currying pero primero hacer los test TDD
export function getCarsMatchingCriteria(repository:matching){
  return async function(criteria:CriteriaJSON){
    return await matching(criteria)
  }
}
*/
