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
// recibe algo asi 17.500€ o 17.500 €
export const CarPrice = (price = "0") => {
  // quitar punto, simbolo y posible espacio
  const formattedPrice = Number(
    price.toString().replace(".", "").replace("€", "").trim()
  );
  console.log(formattedPrice);
  return carPriceSchema.parse(formattedPrice);
};
