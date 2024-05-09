import { CriteriaJSON } from "../../../domain/criteria/Criteria";
import { GetCarsMatchingCriteria } from "../../../domain/car/GetCarsMatchingCriteriaRepository";


export function getCarsMatchingCriteria(repository:GetCarsMatchingCriteria){
  return async function(criteria:CriteriaJSON){
    return await repository(criteria);
  }
}

