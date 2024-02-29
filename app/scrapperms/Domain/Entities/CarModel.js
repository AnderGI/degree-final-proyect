import crypto from "crypto";
import { CarId } from "../ValueObjects/CarId.js";
import { CarTitle } from "../ValueObjects/CarTitle.js";
import { CarDescription } from "../ValueObjects/CarDescription.js";
import { CarImageURL, CarURL } from "../ValueObjects/CarURL.js";

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
  imageURL: CarImageURL(imageURL),
  source: CarURL(source),
  currentPrice: CarPrice(currentPrice),
  betAmount: CarBetAmount(betAmount),
  reservePrice: CarPrice(reservePrice),
});

/*
{
  id: crypto.randomUUID(),
  title,
  description,
  imageURL,
  source,
  currentPrice,
  betAmount,
  reservePrice,
}
*/
