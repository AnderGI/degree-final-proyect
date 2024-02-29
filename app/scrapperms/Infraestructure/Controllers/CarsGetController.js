import { ObtenerCochesScrapeados } from "../../Application/ObtenerCochesScrappeadosUseCase/ObtenerCochesScrappeados.js";
import { PuppeteerScrapper } from "../ScrapperImplementations/PuppeteerScraper.js";

const pupperterScrapper = PuppeteerScrapper();
const implementations = [pupperterScrapper];

export const getAllCars = async (req, res) => {
  const data = await ObtenerCochesScrapeados(implementations);
  res.json(data);
};
