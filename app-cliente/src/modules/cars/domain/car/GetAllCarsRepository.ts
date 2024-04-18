
import { mockRandomUUUID } from "../utils/getRandomUUID";
import { Car } from "./Car";


export type GetAllCars = () => Promise<Car[]>;

export const fakeCarsArray:Car[] = [{
    id :  {
        value : mockRandomUUUID()
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
},
{
    id :  {
        value : mockRandomUUUID()
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
}]

export const MockGetAllCars: GetAllCars = async () => {
    return Promise.resolve(fakeCarsArray)
}

export const MockGetAllCarsEmpty: GetAllCars = async () => {
    return Promise.resolve([]);
}