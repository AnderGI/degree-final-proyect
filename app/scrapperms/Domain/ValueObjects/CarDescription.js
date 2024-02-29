import { z } from "zod";

const carDescriptionSchema = z
  .string({
    invalid_type_error: "Description must be a string",
  })
  .trim()
  .min(20, { message: "Must be 20 or more characters long" })
  .default("Car with no description");

// Validaciones strictas por defecto lanza una excepcción
export const CarDescription = (description) =>
  carDescriptionSchema.parse(description);
