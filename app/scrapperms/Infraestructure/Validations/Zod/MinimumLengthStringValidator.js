import { z } from "zod";

// Validaciones strictas por defecto lanza una excepcción
export const MinimumLengthStringValidator = (
  toValidateField,
  field,
  length,
  defaultMessage
) => {
  const schema = z
    .string({
      required_error: `${field} is required`,
      invalid_type_error: `${field} must be a string`,
    })
    .trim()
    .min(length, { message: `Must be ${length} or more characters long` })
    .default(defaultMessage);
  return schema.parse(toValidateField);
};
