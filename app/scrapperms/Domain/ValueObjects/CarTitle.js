import { z } from "zod";

const carTitleSchema = z
  .string({
    required_error: "Title is required",
    invalid_type_error: "Title must be a string",
  })
  .trim()
  .min(10, { message: "Must be 10 or more characters long" });
//.default("Unamed Car"); Lo quito por ser un campo obligatorio

// Validaciones strictas por defecto lanza una excepcción
export const CarTitle = (title) => carTitleSchema.parse(title);
