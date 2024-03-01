import { DomainEventPublisher } from "../../Domain/Ports/DomainEventPublisherInterface.js";
import amqp from "amqplib";
const queue = "car_scrapping";
export const RabbitMQDomainEventPublisher = (data) => {
  const proto = {
    publishEvent: async () => {
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
    },
  };

  const composite = DomainEventPublisher(proto);

  return Object.assign(Object.create(composite), proto);
};
