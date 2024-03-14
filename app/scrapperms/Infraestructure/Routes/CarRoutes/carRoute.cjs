const { Router } = require("express");
const { scrappAllCars } = require("../../Controllers/CarsGetController.cjs");

const carRoutes = Router();

carRoutes.get("/", scrappAllCars);

module.exports = { carRoutes };
