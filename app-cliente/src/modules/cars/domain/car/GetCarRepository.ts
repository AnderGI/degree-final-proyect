import { Car, CarId } from "./Car";

export type GetCar = ({value}: CarId) => Promise<Car>; 

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