import { GetAllCars } from "../../../domain/car/GetAllCarsRepository";

export function getAll(repository:GetAllCars){
    return async function(){
        return await repository();
    }
}
