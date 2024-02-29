import { z } from "zod";

const carBetAmountSchema = z;
number({
  invalid_type_error: "Price must be a number",
})
  .finite()
  .safe()
  .nonnegative();

// Validaciones strictas por defecto lanza una excepcción
export const CarBetAmount = (betAmount) => carBetAmountSchema.parse(betAmount);
