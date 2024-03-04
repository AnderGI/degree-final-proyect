import { ObtenerCochesScrapeados } from "../../Application/ObtenerCochesScrappeadosUseCase/ObtenerCochesScrappeados.js";
import { partialCarValidation } from "../../Domain/Services/CarService.js";
import { RabbitMQDomainEventPublisher } from "../DomainEventPublisherImplementations/RabbitMQDomainEventPublisher.js";
import { AutoocasionScrapper } from "../ScrapperImplementations/AutoocasionScrapper.js";
import { SoulAutoScrapper } from "../ScrapperImplementations/SoulAutoScraper.js";

const soulAutoScrapper = SoulAutoScrapper();
const autoocasionScrapper = AutoocasionScrapper();
const implementations = [soulAutoScrapper, autoocasionScrapper];

const queue = "car_scrapping";

export const scrappAllCars = async (req, res) => {
  // Caso de uso de scrappear todos los coches
  let data = await ObtenerCochesScrapeados(implementations);

  // Validar info
  data = data.map((coche) => partialCarValidation(coche));
  // Publicar un evento de dominio mediante rabbit
  const eventPublisher = RabbitMQDomainEventPublisher(data);

  // Validar el tipo del event publisher
  // No creo que sea la mejor forma para validar porque el metodo
  // ya queda publisher al scrappAllCars
  if (eventPublisher.type !== "DomainEventPublisher") {
    return res.json({ error: "Invalid DomainEventPublisherType" });
  }

  // publicar evento
  eventPublisher.publishEvent(data);

  res.json(data);

  // Consumer data ===> Car Microservice in Java
  /*
  (async () => {
    try {
      const connection = await amqp.connect("amqp://localhost");
      const channel = await connection.createChannel();

      process.once("SIGINT", async () => {
        await channel.close();
        await connection.close();
      });

      await channel.assertQueue(queue, { durable: false });

      /*await channel.consume(
        queue,
        (message) => {
          if (message) {
            console.log(
              " [x] Received '%s'",
              JSON.stringify(JSON.parse(message.content.toString()))
            );
          }
        },
        { noAck: true }
      );

      console.log(" [*] Waiting for messages. To exit press CTRL+C");
    } catch (err) {
      console.warn(err);
    }
  })();*/
};
