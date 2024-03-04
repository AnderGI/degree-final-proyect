import { CarId } from "../ValueObjects/CarId.js";
import { CarTitle } from "../ValueObjects/CarTitle.js";
import { CarDescription } from "../ValueObjects/CarDescription.js";
import { CarURL } from "../ValueObjects/CarURL.js";
import { CarPrice } from "../ValueObjects/CarPrice.js";
import { CarBrand } from "../ValueObjects/CarBrand.js";

export const CarModel = ({
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
  id: CarId(),
  title: CarTitle(title),
  description: CarDescription(description),
  brand: CarBrand(brand),
  carImageURL: CarURL(carImageURL),
  carAnnouncement: CarURL(carAnnouncement),
  price: CarPrice(price),
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
