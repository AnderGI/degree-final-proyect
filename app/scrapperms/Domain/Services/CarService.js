import z from "zod";
import { CarModel } from "../Entities/CarModel.js";

const carSchema = z.object({
  id: z.string().uuid(),
  title: z.string(),
  description: z.string(),
  brand: z.string(),
  carImageURL: z.string().url(),
  carAnnouncement: z.string().url(),
  price: z.number(),
  detailsInfo: z.string(),
});

export const partialCarValidation = (cochesData) => {
  const car = CarModel(cochesData);
  return carSchema.partial().parse(car);
};
