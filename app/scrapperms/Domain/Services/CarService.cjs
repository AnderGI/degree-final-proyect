const z = require("zod");
const { CarModel } = require("../Entities/CarModel.cjs");

const carSchema = z.object({
  id: z.string().uuid(),
  title: z.string(),
  description: z.string(),
  brand: z.string(),
  carImageURL: z.string().url(),
  carAnnouncement: z.string().url(),
  price: z.string(),
  detailsInfo: z.string(),
});

const partialCarValidation = (cochesData) => {
  const car = CarModel(cochesData);
  return carSchema.partial().parse(car);
};

module.exports = { partialCarValidation };
