import { CarBrand } from "./Car";

export type GetAllCarBrands = () => Promise<CarBrand[]>;

export const MockGetAllCarBrands = async () => {
    return Promise.resolve([{value:'mmm'}, {value:'hasj'}])
}