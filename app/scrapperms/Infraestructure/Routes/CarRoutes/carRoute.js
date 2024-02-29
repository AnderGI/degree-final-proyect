import { Router } from "express";
import { PuppeteerScrapper } from "../../ScrapperImplementations/PuppeteerScraper.js";

const scrapper = PuppeteerScrapper();

export const carRoutes = Router();

carRoutes.get("/", (req, res) => {
  res.send("Funcion");
});

carRoutes.get("/cars", async (req, res) => {
  const data = await scrapper.scrappWeb();
  console.log(data);
  res.json(data);
});
