import { ObtenerCochesScrapeados } from "../../Application/ObtenerCochesScrappeadosUseCase/ObtenerCochesScrappeados.js";
import { RabbitMQDomainEventPublisher } from "../DomainEventPublisherImplementations/RabbitMQDomainEventPublisher.js";
import { PuppeteerScrapper } from "../ScrapperImplementations/PuppeteerScraper.js";
import amqp from "amqplib";

const pupperterScrapper = PuppeteerScrapper();
const implementations = [pupperterScrapper];

const queue = "car_scrapping";

export const getAllCars = async (req, res) => {
  const data = await ObtenerCochesScrapeados(implementations);

  const rabbitMq = RabbitMQDomainEventPublisher(data);

  rabbitMq.publishEvent(data);

  res.json(data);

  // Consumer data ===> Car Microservice in Java
  (async () => {
    try {
      const connection = await amqp.connect("amqp://localhost");
      const channel = await connection.createChannel();

      process.once("SIGINT", async () => {
        await channel.close();
        await connection.close();
      });

      await channel.assertQueue(queue, { durable: false });
      await channel.consume(
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
  })();
};
