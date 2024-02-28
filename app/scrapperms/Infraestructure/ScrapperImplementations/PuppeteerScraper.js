import { Scrapper } from "../../domain/ports/ScrapperInterface";

const PuppeteerScrapper = () => {
  // Crear la implementación
  const basicData = {
    scrappWeb: (args) => null,
  };

  // Establecer el prototipo para poder acceder al type de Scrapper
  const composite = Scrapper(basicData);

  // Devolver un obj que tenga el basicdata y
  // como prototipo un objeto cuyo prototipo es composite
  // con el fin de generar una abstracción cuyo type es scrapper
  return Object.assign(Object.create(composite), basicData);
};
