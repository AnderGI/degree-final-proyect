import { ObtenerCochesScrapeados } from "../../Application/ObtenerCochesScrappeadosUseCase/ObtenerCochesScrappeados.js";
import { PuppeteerScrapper } from "../ScrapperImplementations/PuppeteerScraper.js";

export const getAllCars = async (req, res) => {
  const pupperterScrapper = PuppeteerScrapper();
  const data = await ObtenerCochesScrapeados([pupperterScrapper]);
  res.json(data);
};
