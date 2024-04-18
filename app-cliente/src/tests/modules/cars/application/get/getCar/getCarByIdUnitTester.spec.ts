import { getCar } from "../../../../../../modules/cars/application/get/getCar/getCarById"
import { Car, CarId } from "../../../../../../modules/cars/domain/car/Car"
import { MockGetCar } from "../../../domain/car/MockCarRepository"

describe('Get car by id', () => {
    it('It should return a car with same id as the mock api call', async () => {
        const fakeCarId:CarId = {
            // esto tendria que modificarlo para aunque sea en tiempo de compilacion
            // typescript lo valide como un uuid string-string-string-string-string
            value : 'jabsjdfb' 
        }

        const data:Car = await getCar(MockGetCar)(fakeCarId);
        const {id}= data;
        expect(id.value).toEqual(fakeCarId.value)
    })
})