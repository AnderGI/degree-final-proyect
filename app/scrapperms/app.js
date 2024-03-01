import express from "express";
import { carRoutes } from "./Infraestructure/Routes/CarRoutes/carRoute.js";
import { CochesNetScrapper } from "./Infraestructure/ScrapperImplementations/CochesNetScrapper.js";

const app = express();

// JSON Middleware
// Coge las peticiones POST y que tengan el application-type a json solo
// y añade al cuerpo de la request el json
/*
app.use(express.json());
app.use(carRoutes);
app.disable("x-powered-by");

const PORT = 3000;

app.listen(PORT, () => {
  console.log(`Server listening at http://localhost:${PORT}`);
});
*/

const scrapper = CochesNetScrapper();
scrapper.scrappWeb();
