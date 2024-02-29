// Este método va a recibir uno o varios scrappeadores de distintas páginas web
// la idea es respetar la interfaz de Scrapper para no violar el principio de DIP
// y de ese modo trabajar contra la abstracción
// Este método por cada scrapeador
// Validara los modelos Car
// los añadira a una lista conjunta y lo devolverá
export const ObtenerCochesScrapeados = async (scrappers = []) => {
  const cochesData = [];
  for (const scrapper of scrappers) {
    if (scrapper.type === "scrapper") {
      cochesData.push(...(await scrapper.scrappWeb()));
    }
  }

  return cochesData;
};
