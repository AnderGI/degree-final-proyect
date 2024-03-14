// Este método va a recibir uno o varios scrappeadores de distintas páginas web
// la idea es respetar la interfaz de Scrapper para no violar el principio de DIP
// y de ese modo trabajar contra la abstracción
// Este método por cada scrapeador
// Validara los modelos Car

const {
  partialCarValidation,
} = require("../../Domain/Services/CarService.cjs");

// los añadira a una lista conjunta y lo devolverá
const ObtenerCochesScrapeados = async (scrappers = []) => {
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
        title: coche.title ?? "Titulo de coche por error",
        description: coche.description ?? "Descripccion de coche por error",
        brand: coche.brand ?? "Marca de coche por error",
        carImageURL: coche.carImageURL ?? "Imagen de coche pro error",
        carAnnouncement: coche.carAnnouncement ?? "Anuncio d coche por error",
        price: coche.price ?? "0€",
      });
    }
  });
  return cochesData;
};

module.exports = { ObtenerCochesScrapeados };
