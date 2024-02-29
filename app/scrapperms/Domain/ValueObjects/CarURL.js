import { z } from "zod";

const carUrlSchema = z
  .string({
    invalid_type_error: "The image url must be a string",
  })
  .trim()
  .url({ message: "Invalid URL" })
  .default("Unamed Car");

export const CarURL = (imageURL) => carUrlSchema.parse(imageURL);
