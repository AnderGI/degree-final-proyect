import { Scrapper } from "../../domain/ports/ScrapperInterface.js";
import puppeteer from "puppeteer";
import { SoulAutoConstConfig } from "../WebConstants/SoulAutoConsts.js";

const SOUL_AUTO_URL_MAIN_PAGE = "https://soulauto.com/";

// Puppeter.evaluate(pageFunc, args) https://pptr.dev/api/puppeteer.page.evaluate
// Este método se ejecuta en el contexto del navegador
// por eso que sea necesario pasarle los selectores ya que no se ejecutan en el
// contaxto de Node

export const PuppeteerScrapper = () => {
  // Crear la implementación
  const basicData = {
    scrappWeb: async () => {
      const browser = await puppeteer.launch({
        headless: "new",
      });

      const page = await browser.newPage();

      await page.goto(SOUL_AUTO_URL_MAIN_PAGE);

      // Hacemos un destructuring de SoulAutoConstConfig
      const data = await page.evaluate(
        ({
          SOUL_AUTO_MAIN_PAGE_CAR_DOM_SELECTOR,
          CAR_OF_LIST_OF_CARDS_CAR_DESCRIPTION_SELECTOR,
          CAR_OF_LIST_OF_CARDS_CAR_IMAGE_URL_SELECTOR,
          CAR_OF_LIST_OF_CARDS_CAR_TITLE_SELECTOR,
          CAR_OF_LIST_OF_CARDS_CAR_PRICE_SELECTOR,
          CAR_OF_LIST_OF_CARDS_CAR_RESERVE_PRICE_SELECTOR,
          CAR_OF_LIST_OF_CARDS_CAR_BET_AMOUNT_SELECTOR,
        }) => {
          const data = [];
          const carsList = [
            ...document.querySelectorAll(SOUL_AUTO_MAIN_PAGE_CAR_DOM_SELECTOR),
          ];

          for (const car of carsList) {
            const description = car.querySelector(
              CAR_OF_LIST_OF_CARDS_CAR_DESCRIPTION_SELECTOR
            ).innerText;
            const imageURL = car.querySelector(
              CAR_OF_LIST_OF_CARDS_CAR_IMAGE_URL_SELECTOR
            ).href;
            const title = car.querySelector(
              CAR_OF_LIST_OF_CARDS_CAR_TITLE_SELECTOR
            ).innerText;
            const currentPrice = car.querySelector(
              CAR_OF_LIST_OF_CARDS_CAR_PRICE_SELECTOR
            ).innerText;
            const reservePrice = car.querySelector(
              CAR_OF_LIST_OF_CARDS_CAR_RESERVE_PRICE_SELECTOR
            ).attributes.reserve_price.value;
            const betAmount = car.querySelector(
              CAR_OF_LIST_OF_CARDS_CAR_BET_AMOUNT_SELECTOR
            ).innerText;
            data.push({
              id: crypto.randomUUID(),
              title,
              description,
              imageURL,
              source: "https://soulauto.com/",
              currentPrice,
              betAmount,
              reservePrice,
            });
          }

          return data;
        },
        SoulAutoConstConfig
      );
      console.log(data);
      await page.close();
    },
  };

  // Establecer el prototipo para poder acceder al type de Scrapper
  const composite = Scrapper(basicData);

  // Devolver un obj que tenga el basicdata y
  // como prototipo un objeto cuyo prototipo es composite
  // con el fin de generar una abstracción cuyo type es scrapper
  return Object.assign(Object.create(composite), basicData);
};
