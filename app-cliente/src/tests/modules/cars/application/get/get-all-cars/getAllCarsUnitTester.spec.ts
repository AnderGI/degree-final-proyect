import { getAll } from "../../../../../../modules/cars/application/get/getAllCars/getAllCars"
import { Car } from "../../../../../../modules/cars/domain/car/Car"
import { MockGetAllCars } from "../../../../../../modules/cars/domain/car/GetAllCarsRepository"
import { fakeCarsArray } from "../../../../../../modules/cars/domain/car/GetAllCarsRepository"
describe('Get all cars', () => {
    it('It should return an array of cars', async () => {
        const cars:Car[] = await getAll(MockGetAllCars)()
        cars.forEach((car, index, cars) => {
            // Agregar alguna funcion de utilidad que haga un deep equality de los values de los VO de Car
            expect(cars[index].id.value).toBe(fakeCarsArray[index].id.value)
            expect(cars[index].title.value).toBe(fakeCarsArray[index].title.value)
            expect(cars[index].description.value).toBe(fakeCarsArray[index].description.value)
            expect(cars[index].brand.value).toBe(fakeCarsArray[index].brand.value)
            expect(cars[index].carAnnouncementURL.value).toBe(fakeCarsArray[index].carAnnouncementURL.value)
            expect(cars[index].carImageURL.value).toBe(fakeCarsArray[index].carImageURL.value)
            expect(cars[index].price.value).toBe(fakeCarsArray[index].price.value)
        });
    })
})