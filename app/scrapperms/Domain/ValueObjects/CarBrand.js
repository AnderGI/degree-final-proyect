import { z } from "zod";

export const carBrandSchema = z
  .string({
    required_error: "Brand is required",
    invalid_type_error: "Brand must be a string",
  })
  .trim()
  .default("Car with no brand");

// Validaciones strictas por defecto lanza una excepcción
export const CarBrand = (brand) => carBrandSchema.parse(brand);
