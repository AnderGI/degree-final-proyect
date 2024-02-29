import { Router } from "express";
import { PuppeteerScrapper } from "../../ScrapperImplementations/PuppeteerScraper.js";
import { getAllCars } from "../../Controllers/CarsGetController.js";

const scrapper = PuppeteerScrapper();

export const carRoutes = Router();

carRoutes.get("/", (req, res) => {
  res.send("Funcion");
});

carRoutes.get("/cars", getAllCars);
