// Esto sería una especie de interfaz en JS
// JS no tiene interfaces => composición de funciones

const Scrapper = (scrapperImpl) => ({
  type: "scrapper",
  scrappWeb: () => scrapperImpl.scrappWeb(),
});

module.exports = { Scrapper };
