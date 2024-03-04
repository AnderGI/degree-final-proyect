import { Scrapper } from "../../domain/ports/ScrapperInterface.js";
import puppeteer from "puppeteer";
import { SoulAutoDomSelectors } from "../WebConstants/SoulAutoConsts.js";
import { CarModel } from "../../Domain/Entities/CarModel.js";

const SOUL_AUTO_URL_MAIN_PAGE = "https://soulauto.com/";

// Puppeter.evaluate(pageFunc, args) https://pptr.dev/api/puppeteer.page.evaluate
// Este método se ejecuta en el contexto del navegador
// por eso que sea necesario pasarle los selectores ya que no se ejecutan en el
// contaxto de Node

export const SoulAutoScrapper = () => {
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
          ALL_CARS_LIST_DOM_SELECTOR,
          CAR_TITLE_DOM_SELECTOR_QUERY_FROM_CAR,
          CAR_PRICE_DOM_SELECTOR_QUERY_FROM_CAR,
          CAR_DESCRIPTION_DOM_SELECTOR_QUERY_FROM_CAR,
          CAR_ANNOUNCMENT_DOM_SELECTOR_FROM_CAR,
          CAR_IMAGE_URL_DOM_SELECTOR_QUERY_FROM_CAR,
          CAR_OF_LIST_OF_CARDS_CAR_RESERVE_PRICE_SELECTOR,
          CAR_OF_LIST_OF_CARDS_CAR_BET_AMOUNT_SELECTOR,
        }) => {
          const data = [];
          const carsList = [
            ...document.querySelectorAll(ALL_CARS_LIST_DOM_SELECTOR),
          ];

          for (const car of carsList) {
            const description = car.querySelector(
              CAR_DESCRIPTION_DOM_SELECTOR_QUERY_FROM_CAR
            ).innerText;
            const carAnnouncement = car.querySelector(
              CAR_ANNOUNCMENT_DOM_SELECTOR_FROM_CAR
            ).href;
            const title = car.querySelector(
              CAR_TITLE_DOM_SELECTOR_QUERY_FROM_CAR
            ).innerText;
            const price = car.querySelector(
              CAR_PRICE_DOM_SELECTOR_QUERY_FROM_CAR
            ).innerText;
            const carImageURL = car.querySelector(
              CAR_IMAGE_URL_DOM_SELECTOR_QUERY_FROM_CAR
            ).src;
            const reservePrice = car.querySelector(
              CAR_OF_LIST_OF_CARDS_CAR_RESERVE_PRICE_SELECTOR
            ).attributes.reserve_price.value;
            const betAmount = car.querySelector(
              CAR_OF_LIST_OF_CARDS_CAR_BET_AMOUNT_SELECTOR
            ).innerText;
            const SELECT_CAR_BRAND = (car) => {
              const classList = [...car.classList];
              const CAR_CLASS = classList.find(
                (string) => string.indexOf("product_cat") !== -1
              );
              const carBrand = CAR_CLASS.replace("product_cat", "").substring(
                1
              );
              return carBrand;
            };
            const carBrand = SELECT_CAR_BRAND(car);
            data.push({
              title,
              description,
              brand: carBrand,
              carImageURL,
              carAnnouncement,
              price,
              //betAmount,
              //reservePrice,
            });
          }

          return data;
        },
        SoulAutoDomSelectors
      );
      await page.close();

      return data;
    },
  };

  // Establecer el prototipo para poder acceder al type de Scrapper
  const composite = Scrapper(basicData);

  // Devolver un obj que tenga el basicdata y
  // como prototipo un objeto cuyo prototipo es composite
  // con el fin de generar una abstracción cuyo type es scrapper
  return Object.assign(Object.create(composite), basicData);
};
