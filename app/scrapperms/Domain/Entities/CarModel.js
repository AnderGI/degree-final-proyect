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
  id: CarId(),
  title: CarTitle(title),
  description: CardDescription(description),
  imageURL: CardImageURL(imageURL),
  source: CardSource(source),
  currentPrice: CardPrice(currentPrice),
  betAmount: CardBetAmount(betAmount),
  reservePrice: CardPrice(reservePrice),
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
