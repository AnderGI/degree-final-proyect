import { CriteriaJSON } from "../criteria/Criteria";
import { Car } from "./Car";

export type GetCarsMatchingCriteria = (criteria:CriteriaJSON) => Promise<Car[]>;

export const MockGetCarsMatchingCriteria = async (criteria:CriteriaJSON) => {
    return Promise.resolve([])
}