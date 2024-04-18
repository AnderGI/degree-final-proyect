import { getAllCarBrands } from "../../../../../../modules/cars/application/get/getAllCarBrands/getAllCarBrands"
import { CarBrand } from "../../../../../../modules/cars/domain/car/Car"
import { MockGetAllCarBrands } from "../../../../../../modules/cars/domain/car/GetCarBrandsRepository"

describe('Get all car brands', () => {
    it('It should return an array of car brands', async () => {
        const carBrands:CarBrand[] = await getAllCarBrands(MockGetAllCarBrands)();
        expect(carBrands.length).toBeTruthy() // 0 es falsy
    })
})