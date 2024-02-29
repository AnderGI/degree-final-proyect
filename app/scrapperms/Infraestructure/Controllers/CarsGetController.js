import { ObtenerCochesScrapeados } from "../../Application/ObtenerCochesScrappeadosUseCase/ObtenerCochesScrappeados.js";
import { PuppeteerScrapper } from "../ScrapperImplementations/PuppeteerScraper.js";
import amqp from "amqplib";

const pupperterScrapper = PuppeteerScrapper();
const implementations = [pupperterScrapper];

const queue = "car_scrapping";

export const getAllCars = async (req, res) => {
  const data = await ObtenerCochesScrapeados(implementations);
  (async () => {
    let connection;
    try {
      connection = await amqp.connect("amqp://localhost");
      const channel = await connection.createChannel();

      await channel.assertQueue(queue, { durable: false });
      channel.sendToQueue(queue, Buffer.from(JSON.stringify(data)));
      console.log(" [x] Sent '%s'", JSON.stringify(data));
      await channel.close();
    } catch (err) {
      console.warn(err);
    } finally {
      if (connection) await connection.close();
    }
  })();

  res.json(data);

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
