import crypto from "crypto";

export const CarModel = ({
  title,
  description,
  imageURL,
  source,
  currentPrice,
  betAmount,
  reservePrice,
}) => ({
  id: crypto.randomUUID(),
  title,
  description,
  imageURL,
  source,
  currentPrice,
  betAmount,
  reservePrice,
});
