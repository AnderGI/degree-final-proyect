const { z } = require("zod");

const URLStringValidator = (toValidateField, field, defaultMessage) => {
  const schema = z
    .string({
      invalid_type_error: `The ${field} url must be a string`,
    })
    .trim()
    .url({ message: "Invalid URL" })
    .default(defaultMessage);
  return schema.parse(toValidateField);
};

module.exports = { URLStringValidator };
