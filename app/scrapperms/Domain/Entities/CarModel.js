import { CarId } from "../ValueObjects/CarId.js";
import { CarTitle } from "../ValueObjects/CarTitle.js";
import { CarDescription } from "../ValueObjects/CarDescription.js";
import { CarURL } from "../ValueObjects/CarURL.js";
import { CarPrice } from "../ValueObjects/CarPrice.js";
import { CarBetAmount } from "../ValueObjects/CarBetAmount.js";
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
  //carDetailsInfo = null,
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
  //carDetailsInfo,
});

/*
title
description
brand
carImageURL
carAnnouncement
price
detailsInfo
*/
