import { Router } from "express";
import { PuppeteerScrapper } from "../../ScrapperImplementations/PuppeteerScraper.js";
import { scrappAllCars } from "../../Controllers/CarsGetController.js";

const scrapper = PuppeteerScrapper();

export const carRoutes = Router();

carRoutes.get("/", async (req, res) => await scrappAllCars(req, res));
