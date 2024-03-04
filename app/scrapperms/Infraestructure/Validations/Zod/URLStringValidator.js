import { z } from "zod";

export const URLStringValidator = (toValidateField, field, defaultMessage) => {
  const schema = z
    .string({
      invalid_type_error: `The ${field} url must be a string`,
    })
    .trim()
    .url({ message: "Invalid URL" })
    .default(defaultMessage);
  return schema.parse(toValidateField);
};
