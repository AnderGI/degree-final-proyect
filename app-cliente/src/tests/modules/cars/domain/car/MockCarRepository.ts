import { Car, CarId } from "../../../../../modules/cars/domain/car/Car";
import { GetCar } from "../../../../../modules/cars/domain/car/CarRepository";

// Realmente solo me interesaria saber que el Car que se devuelve tiene e mismo CarId
// Podria utilizar el tipo Partial pero eso modificaria el use case a testear
// Habia pensado en hacer algo itermedio pero creo que es mas lio que otra cosa

export const MockGetCar:GetCar = async ({value}:CarId) => {
    return Promise.resolve({
        id :  {
            value
        },
        title : {
            value : 'III'
        },
        description : {
            value : 'qqq'
        },
        brand : {
            value : 'qqq'
        },
        carImageURL: {
            value : 'qqq'
        },
        carAnnouncementURL : {
            value : 'qqq'
        },
        price : {
            value : 1250
        }
    })
};