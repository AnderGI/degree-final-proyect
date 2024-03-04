import { z } from "zod";

export const PriceFromStringValidator = (price) => {
  const carPriceSchema = z
    .number({
      required_error: "Price is required",
      invalid_type_error: "Price must be a number",
    })
    .finite()
    .safe()
    .positive();
  const formattedPrice = Number(
    price.toString().replace(".", "").replace("€", "").trim()
  );

  return carPriceSchema.parse(formattedPrice);
};
