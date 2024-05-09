const express = require("express");
const {
  carRoutes,
} = require("./Infraestructure/Routes/CarRoutes/carRoute.cjs");

const app = express();

// JSON Middleware
// Coge las peticiones POST y que tengan el application-type a json solo
// y añade al cuerpo de la request el json
app.use(express.json());
// Enrutador
app.use(carRoutes);
app.disable("x-powered-by");

const PORT = 3001;

app.listen(PORT, () => {
  console.log(`Server listening at http://localhost:${PORT}`);
});
