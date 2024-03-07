import { Scrapper } from "../../domain/ports/ScrapperInterface.js";
import puppeteer from "puppeteer";
import { AutoocasionDomSelectors } from "../WebConstants/AutoocasionConst.js";

const AUTOOCASION_URL_MAIN_PAGE =
  "https://www.autocasion.com/venta-coches-clasicos";

// Puppeter.evaluate(pageFunc, args) https://pptr.dev/api/puppeteer.page.evaluate
// Este método se ejecuta en el contexto del navegador
// por eso que sea necesario pasarle los selectores ya que no se ejecutan en el
// contaxto de Node

export const AutoocasionScrapper = () => {
  // Crear la implementación
  const basicData = {
    scrappWeb: async () => {
      const browser = await puppeteer.launch({
        headless: "new",
      });

      const page = await browser.newPage();

      await page.goto(AUTOOCASION_URL_MAIN_PAGE);
      // Hacemos un destructuring de SoulAutoConstConfig
      const data = await page.evaluate(
        ({
          ALL_CARS_LIST_DOM_SELECTOR,
          CAR_TITLE_DOM_SELECTOR_QUERY_FROM_CAR,
          CAR_PRICE_DOM_SELECTOR_QUERY_FROM_CAR,
          CAR_DESCRIPTION_DOM_SELECTOR_QUERY_FROM_CAR,
          CAR_ANNOUNCMENT_DOM_SELECTOR_FROM_CAR,
          CAR_IMAGE_URL_DOM_SELECTOR_QUERY_FROM_CAR,
        }) => {
          console.log(
            document.querySelector(
              "div.container-fluid.listado article.anuncio"
            )
          );
          const data = [];
          const carsList = [
            ...document.querySelectorAll(ALL_CARS_LIST_DOM_SELECTOR),
          ];
          for (const car of carsList) {
            const title = car.querySelector(
              CAR_TITLE_DOM_SELECTOR_QUERY_FROM_CAR
            ).innerText;
            const price = car.querySelector(
              CAR_PRICE_DOM_SELECTOR_QUERY_FROM_CAR
            ).innerText;
            const description = car.querySelector(
              CAR_DESCRIPTION_DOM_SELECTOR_QUERY_FROM_CAR
            ).innerText;
            const brand = title.substring(0, title.indexOf(" ")).toLowerCase();
            const carAnnouncement = car.querySelector(
              CAR_ANNOUNCMENT_DOM_SELECTOR_FROM_CAR
            ).href;
            const carImageURL = car.querySelector(
              CAR_IMAGE_URL_DOM_SELECTOR_QUERY_FROM_CAR
            ).srcset;
            data.push({
              title,
              description,
              brand,
              carImageURL,
              carAnnouncement,
              price,
            });
          }

          return data;
        },
        AutoocasionDomSelectors
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
