import { Router } from "express";
import { scrappAllCars } from "../../Controllers/CarsGetController.js";

export const carRoutes = Router();

carRoutes.get("/", scrappAllCars);
