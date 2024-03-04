import { Scrapper } from "../../domain/ports/ScrapperInterface.js";
import puppeteer from "puppeteer";
import { AutoocasionConstConfig } from "../WebConstants/AutoocasionConst.js";

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
          ALL_ANUNCIOS_COCHE_FROM_CONTENEDOR_DOM_SELECTOR,
          TITULO_COCHE_DOM_SELECTOR,
          PRECIO_COCHE_DOM_SELECTOR,
          DETAILS_COCHE_DOMSELECTOR,
        }) => {
          const data = [];
          const carsList = [
            ...document.querySelectorAll(
              ALL_ANUNCIOS_COCHE_FROM_CONTENEDOR_DOM_SELECTOR
            ),
          ];
          for (const car of carsList) {
            const title = car.querySelector(
              TITULO_COCHE_DOM_SELECTOR
            ).innerText;
            const precio = car.querySelector(
              PRECIO_COCHE_DOM_SELECTOR
            ).innerText;
            const detailsInfo = car.querySelector(
              DETAILS_COCHE_DOMSELECTOR
            ).innerText;
            const marca = title.substring(0, title.indexOf(" ")).toLowerCase();
            const carAnnouncement = car.querySelector("a").href;
            data.push({
              title,
              precio,
              detailsInfo,
              marca,
              carAnnouncement,
              source: "https://www.autocasion.com/venta-coches-clasicos",
            });
          }

          return data;
        },
        AutoocasionConstConfig
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
