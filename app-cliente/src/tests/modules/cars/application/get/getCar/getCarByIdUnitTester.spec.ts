import { getCar } from "../../../../../../modules/cars/application/get/getCar/getCarById"
import { Car, CarId } from "../../../../../../modules/cars/domain/car/Car"
import { MockGetCar } from "../../../../../../modules/cars/domain/car/GetCarRepository"
import { mockRandomUUUID } from "../../../../../../modules/cars/domain/utils/getRandomUUID"


describe('Get car by id', () => {
    it('It should return a car with same id as the mock api call', async () => {
        const fakeCarId:CarId = {
            value : mockRandomUUUID()
        }

        const data:Car = await getCar(MockGetCar)(fakeCarId);
        const {id}= data;
        expect(id.value).toEqual(fakeCarId.value)
    })
    
})