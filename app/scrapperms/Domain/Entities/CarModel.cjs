const {
  MinimumLengthStringValidator,
} = require("../../Infraestructure/Validations/Zod/MinimumLengthStringValidator.cjs");
const {
  URLStringValidator,
} = require("../../Infraestructure/Validations/Zod/URLStringValidator.cjs");
const crypto = require("node:crypto");
const {
  PriceFromStringValidator,
} = require("../../Infraestructure/Validations/Zod/PriceFromStringValidator.cjs");
const CarModel = ({
  title,
  description,
  brand,
  carImageURL,
  carAnnouncement,
  price,
  //betAmount,
  //reservePrice,
  //detailsInfo = null,
}) => ({
  id: crypto.randomUUID(),
  title: MinimumLengthStringValidator(title, "Title", 10, "Unnamed Car"),
  description: MinimumLengthStringValidator(
    description,
    "Description",
    20,
    "Car with no description"
  ),
  brand: MinimumLengthStringValidator(brand, "Brand", 1, "Car with no brand"),
  carImageURL: URLStringValidator(carImageURL, "Image", "Bad image url"),
  carAnnouncement: URLStringValidator(
    carAnnouncement,
    "Price",
    "Bad car announcement price"
  ),
  price: PriceFromStringValidator(price),
  //betAmount: CarBetAmount(betAmount),
  //reservePrice: CarPrice(reservePrice),
});

/*
title
description
brand
carImageURL
carAnnouncement
price
detailsInfo
bet?{
  amount,
  reservePrice,
}
*/
module.exports = { CarModel };
