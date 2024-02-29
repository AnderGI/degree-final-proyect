import { CarId } from "../ValueObjects/CarId.js";
import { CarTitle } from "../ValueObjects/CarTitle.js";
import { CarDescription } from "../ValueObjects/CarDescription.js";
import { CarURL } from "../ValueObjects/CarURL.js";
import { CarPrice } from "../ValueObjects/CarPrice.js";
import { CarBetAmount } from "../ValueObjects/CarBetAmount.js";

export const CarModel = ({
  title,
  description,
  imageURL,
  source,
  currentPrice,
  betAmount,
  reservePrice,
}) => ({
  id: CarId(),
  title: CarTitle(title),
  description: CarDescription(description),
  imageURL: CarURL(imageURL),
  source: CarURL(source),
  currentPrice: CarPrice(currentPrice),
  betAmount: CarBetAmount(betAmount),
  reservePrice: CarPrice(reservePrice),
});
