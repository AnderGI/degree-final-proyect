// Este método va a recibir uno o varios scrappeadores de distintas páginas web
// la idea es respetar la interfaz de Scrapper para no violar el principio de DIP
// y de ese modo trabajar contra la abstracción
// Este método por cada scrapeador
// Validara los modelos Car

import { partialCarValidation } from "../../Domain/Services/CarService.js";

// los añadira a una lista conjunta y lo devolverá
export const ObtenerCochesScrapeados = async (scrappers = []) => {
  let cochesData = [];
  for (const scrapper of scrappers) {
    if (scrapper.type === "scrapper") {
      cochesData.push(...(await scrapper.scrappWeb()));
    }
  }

  cochesData = cochesData.map((coche) => {
    let c;
    try {
      c = partialCarValidation(coche);
      return c;
    } catch {
      return partialCarValidation({
        title: "Titulo de coche por error",
        description: "Descripccion de coche por error",
        brand: "Marca de coche por error",
        carImageURL: "Imagen de coche pro error",
        carAnnouncement: "Anuncio d coche por error",
        price: "0€",
      });
    }
  });
  return cochesData;
};
