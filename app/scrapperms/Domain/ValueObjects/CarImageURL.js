import { z } from "zod";

const carImageUrlSchema = z
  .string({
    invalid_type_error: "The image url must be a string",
  })
  .trim()
  .url({ message: "Invalid URL" })
  .default("Unamed Car");

export const CarImageURL = (imageURL) => carImageUrlSchema.parse(imageURL);
