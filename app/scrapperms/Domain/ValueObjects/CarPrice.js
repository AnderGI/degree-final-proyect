import { z } from "zod";

const carPriceSchema = z
  .number({
    required_error: "Price is required",
    invalid_type_error: "Price must be a number",
  })
  .finite()
  .safe()
  .positive();

// Validaciones strictas por defecto lanza una excepcción
export const CarPrice = (price) => carPriceSchema.parse(price);
